package cn.good.domain.activity.service;

import cn.good.domain.activity.model.entity.SkuRechargeEntity;

/**
 * TODO
 *
 * @Description 抽奖活动账户额度服务
 * @Author wkm
 * @Date 2024/10/28
 **/
public interface IRaffleActivityAccountQuotaService {

    /**
     * 以sku创建抽奖活动订单，获得参与抽奖资格（可消耗的次数）
     *
     * @param skuRechargeEntity 活动sku实体，通过sku领取活动。
     * @return 活动参与记录实体
     */

    String createOrder(SkuRechargeEntity skuRechargeEntity);
}
