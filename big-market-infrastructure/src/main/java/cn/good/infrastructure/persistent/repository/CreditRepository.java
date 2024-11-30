package cn.good.infrastructure.persistent.repository;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.good.domain.credit.model.aggregate.TradeAggregate;
import cn.good.domain.credit.model.entity.CreditAccountEntity;
import cn.good.domain.credit.model.entity.CreditOrderEntity;
import cn.good.domain.credit.repository.ICreditRepository;
import cn.good.infrastructure.persistent.dao.IUserCreditAccountDao;
import cn.good.infrastructure.persistent.dao.IUserCreditOrderDao;
import cn.good.infrastructure.persistent.po.UserCreditAccount;
import cn.good.infrastructure.persistent.po.UserCreditOrder;
import cn.good.infrastructure.persistent.redis.IRedisService;
import cn.good.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/30
 **/
@Slf4j
@Repository
public class CreditRepository implements ICreditRepository {
    @Resource
    private IRedisService redisService;
    @Resource
    private IUserCreditAccountDao userCreditAccountDao;
    @Resource
    private IUserCreditOrderDao userCreditOrderDao;
    @Resource
    private IDBRouterStrategy dbRouter;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Override
    public void saveUserCreditTradeOrder(TradeAggregate tradeAggregate) {
        String userId = tradeAggregate.getUserId();
        CreditAccountEntity creditAccountEntity = tradeAggregate.getCreditAccountEntity();
        CreditOrderEntity creditOrderEntity = tradeAggregate.getCreditOrderEntity();

        /* 积分账户 */
        UserCreditAccount userCreditAccountReq = new UserCreditAccount();
        userCreditAccountReq.setUserId(userId);
        userCreditAccountReq.setTotalAmount(creditAccountEntity.getAdjustAmount());
        userCreditAccountReq.setAvailableAmount(creditAccountEntity.getAdjustAmount());
        /* 积分订单 */
        UserCreditOrder userCreditOrderReq = new UserCreditOrder();
        userCreditOrderReq.setUserId(creditOrderEntity.getUserId());
        userCreditOrderReq.setOrderId(creditOrderEntity.getOrderId());
        userCreditOrderReq.setTradeName(creditOrderEntity.getTradeName().getName());
        userCreditOrderReq.setTradeType(creditOrderEntity.getTradeType().getCode());
        userCreditOrderReq.setTradeAmount(creditOrderEntity.getTradeAmount());
        userCreditOrderReq.setOutBusinessNo(creditOrderEntity.getOutBusinessNo());

        RLock lock = redisService.getLock(Constants.RedisKey.USER_CREDIT_ACCOUNT_LOCK + userId + Constants.UNDERLINE + creditOrderEntity.getOutBusinessNo());
        try{
            lock.lock(3, TimeUnit.SECONDS);
            dbRouter.doRouter(userId);
            /* 编程式事务 */
            transactionTemplate.execute(status -> {
                try{
                    /* 保存用户积分*/
                    UserCreditAccount userCreditAccount = userCreditAccountDao.queryUserCreditAccount(userCreditAccountReq);
                    if(null == userCreditAccount){
                        userCreditAccountDao.insert(userCreditAccountReq);
                    }else {
                        userCreditAccountDao.updateAddAmount(userCreditAccountReq);
                    }
                    /*保存账户订单*/
                    userCreditOrderDao.insert(userCreditOrderReq);
                }catch (DuplicateKeyException e){
                    status.setRollbackOnly();
                    log.error("调整账户积分额度异常，唯一索引冲突 userId:{} orderId:{}", userId, creditOrderEntity.getOrderId(), e);
                }
                catch (Exception  e){
                    status.setRollbackOnly();
                    log.error("调整账户积分额度失败 userId:{} orderId:{}", userId, creditOrderEntity.getOrderId(), e);
                }
                return 1;
            });
        }finally {
            dbRouter.clear();
            lock.unlock();
        }
    }
}
