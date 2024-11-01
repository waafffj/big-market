package cn.good.domain.activity.service;

import cn.good.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.good.domain.activity.model.entity.UserRaffleOrderEntity;

/**
 * TODO
 *
 * @Description 抽奖活动参与服务
 * @Author wkm
 * @Date 2024/11/1
 **/
public interface IRaffleActivityPartakeService {
    /**
     * 创建抽奖单,用户参与抽奖，扣减活动库存
     * @param partakeRaffleActivityEntity
     * @return 用户抽奖订单实体
     */
    UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);
}
