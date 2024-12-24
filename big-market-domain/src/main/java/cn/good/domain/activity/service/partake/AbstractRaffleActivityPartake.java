package cn.good.domain.activity.service.partake;

import cn.good.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import cn.good.domain.activity.model.entity.ActivityEntity;
import cn.good.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.good.domain.activity.model.entity.UserRaffleOrderEntity;
import cn.good.domain.activity.model.entity.UserTenRaffleOrderEntity;
import cn.good.domain.activity.model.valobj.ActivityStateVO;
import cn.good.domain.activity.model.valobj.UserRaffleOrderStateVO;
import cn.good.domain.activity.repository.IActivityRepository;
import cn.good.domain.activity.service.IRaffleActivityPartakeService;
import cn.good.types.enums.ResponseCode;
import cn.good.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @Description 抽奖活动参与抽象类   定义流程
 * @Author wkm
 * @Date 2024/11/1
 **/
@Slf4j
public abstract class AbstractRaffleActivityPartake implements IRaffleActivityPartakeService {
    protected final IActivityRepository activityRepository;

    public AbstractRaffleActivityPartake(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public UserRaffleOrderEntity createOrder(String userId, Long activityId) {
        return createOrder(PartakeRaffleActivityEntity.builder()
                .userId(userId)
                .activityId(activityId)
                .build());
    }

    @Override
    public UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity) {
        // 0. 基础信息
        String userId = partakeRaffleActivityEntity.getUserId();
        Long activityId = partakeRaffleActivityEntity.getActivityId();
        Date currentDate = new Date();

        // 1. 活动查询
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activityId);

        // 校验；活动状态
        if (!ActivityStateVO.open.equals(activityEntity.getState())) {
            log.error("创建活动抽奖单失败，活动状态未开启 activityId:{} state:{}", activityId, activityEntity.getState());
            throw new AppException(ResponseCode.ACTIVITY_STATE_ERROR.getCode(), ResponseCode.ACTIVITY_STATE_ERROR.getInfo());
        }
        // 校验；活动日期「开始时间 <- 当前时间 -> 结束时间」
        if (activityEntity.getBeginDateTime().after(currentDate) || activityEntity.getEndDateTime().before(currentDate)) {
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(), ResponseCode.ACTIVITY_DATE_ERROR.getInfo());
        }

        // 3. 额度账户过滤&返回账户构建对象
        CreatePartakeOrderAggregate createPartakeOrderAggregate = this.doFilterAccount(userId, activityId, currentDate);

        // 4. 构建订单
        UserRaffleOrderEntity userRaffleOrder = this.buildUserRaffleOrder(userId, activityId, currentDate);


        // 5. 填充抽奖单实体对象
        createPartakeOrderAggregate.setUserRaffleOrderEntity(userRaffleOrder);

        // 6. 保存聚合对象 - 一个领域内的一个聚合是一个事务操作
        activityRepository.saveCreatePartakeOrderAggregate(createPartakeOrderAggregate);
        log.info("创建活动抽奖单完成 userId:{} activityId:{} orderId:{}", userId, activityId, userRaffleOrder.getOrderId());
        // 7. 返回订单信息
        return userRaffleOrder;
    }
    @Override
    public UserTenRaffleOrderEntity createTenOrders(String userId, Long activityId) {
        UserTenRaffleOrderEntity userTenRaffleOrderEntity = new UserTenRaffleOrderEntity();
        List<String> orders = new ArrayList<>();
        for(int i = 0;i < 10;i ++ ){
            UserRaffleOrderEntity userRaffleOrder = createOrder(PartakeRaffleActivityEntity.builder()
                    .userId(userId)
                    .activityId(activityId)
                    .build());
            orders.add(userRaffleOrder.getOrderId());
        }
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activityId);
        userTenRaffleOrderEntity.setUserId(userId);
        userTenRaffleOrderEntity.setActivityId(activityId);
        userTenRaffleOrderEntity.setActivityName(activityEntity.getActivityName());
        userTenRaffleOrderEntity.setStrategyId(activityEntity.getStrategyId());
        userTenRaffleOrderEntity.setOrderIds(orders);
        userTenRaffleOrderEntity.setOrderTime(new Date());
        userTenRaffleOrderEntity.setOrderState(UserRaffleOrderStateVO.create);
        userTenRaffleOrderEntity.setEndDateTime(activityEntity.getEndDateTime());
        return userTenRaffleOrderEntity;
    }


    protected abstract CreatePartakeOrderAggregate doFilterAccount(String userId, Long activityId, Date currentDate);

    protected abstract UserRaffleOrderEntity buildUserRaffleOrder(String userId, Long activityId, Date currentDate);

}
