package cn.good.domain.credit.service;

import cn.good.domain.credit.model.entity.TradeEntity;

/**
 * TODO
 *
 * @Description 积分调额接口
 * @Author wkm
 * @Date 2024/11/30
 **/
public interface ICreditAdjustService {
    /**
     * 创建增加积分额度订单
     * @param tradeEntity
     * @return
     */
    String createOrder(TradeEntity tradeEntity);
}
