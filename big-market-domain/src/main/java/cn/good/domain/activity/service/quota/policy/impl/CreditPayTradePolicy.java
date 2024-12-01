package cn.good.domain.activity.service.quota.policy.impl;

import cn.good.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.good.domain.activity.model.valobj.OrderStateVO;
import cn.good.domain.activity.repository.IActivityRepository;
import cn.good.domain.activity.service.quota.policy.ITradePolicy;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @Description 积分兑换，支付类订单
 * @Author wkm
 * @Date 2024/12/1
 **/
@Service("credit_pay_trade")
public class CreditPayTradePolicy implements ITradePolicy {
    private final IActivityRepository activityRepository;

    public CreditPayTradePolicy(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate) {
        createQuotaOrderAggregate.setOrderState(OrderStateVO.wait_pay);
        activityRepository.doSaveCreditPayOrder(createQuotaOrderAggregate);
    }
}
