package cn.good.domain.activity.service.quota.policy;

import cn.good.domain.activity.model.aggregate.CreateQuotaOrderAggregate;

/**
 * TODO
 *
 * @Description 交易策略接口，包括；返利兑换（不用支付），积分订单（需要支付）
 * @Author wkm
 * @Date 2024/12/1
 **/
public interface ITradePolicy {
    void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate);
}
