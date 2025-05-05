package cn.good.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.good.infrastructure.persistent.po.RaffleActivityOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @Description 抽奖活动单
 * @Author wkm
 * @Date 2024/10/28
 **/
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IRaffleActivityOrderDao {
   @DBRouter(key = "userId")
    void insert(RaffleActivityOrder raffleActivityOrder);
   @DBRouter
    List<RaffleActivityOrder> queryRaffleActivityOrderByUserId(String userId);
    @DBRouter
    RaffleActivityOrder queryRaffleActivityOrder(RaffleActivityOrder raffleActivityOrderReq);
    int updateOrderCompleted(RaffleActivityOrder raffleActivityOrderReq);
    @DBRouter
    RaffleActivityOrder queryUnpaidActivityOrder(RaffleActivityOrder raffleActivityOrderReq);
}
