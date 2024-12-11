package cn.good.domain.activity.service;

import cn.good.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.good.domain.activity.model.entity.UserRaffleOrderEntity;
import cn.good.domain.activity.model.entity.UserTenRaffleOrderEntity;

/**
 * TODO
 *
 * @Description 抽奖活动参与服务
 * @Author wkm
 * @Date 2024/11/1
 **/
public interface IRaffleActivityPartakeService {
    /**
     * 创建抽奖单；用户参与抽奖活动，扣减活动账户库存，产生抽奖单。如存在未被使用的抽奖单则直接返回已存在的抽奖单。
     *
     * @param userId     用户ID
     * @param activityId 活动ID
     * @return 用户抽奖订单实体对象
     */

    UserRaffleOrderEntity createOrder(String userId,Long activityId);
    /**
     * 创建抽奖单,用户参与抽奖，扣减活动库存
     * @param partakeRaffleActivityEntity
     * @return 用户抽奖订单实体
     */
    UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);

    UserTenRaffleOrderEntity createTenOrders(String userId, Long activityId);
}
