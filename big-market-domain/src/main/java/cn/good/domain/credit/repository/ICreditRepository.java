package cn.good.domain.credit.repository;

import cn.good.domain.credit.model.aggregate.TradeAggregate;

/**
 * TODO
 *
 * @Description 用户积分仓储
 * @Author wkm
 * @Date 2024/11/30
 **/
public interface ICreditRepository {
    void saveUserCreditTradeOrder(TradeAggregate tradeAggregate);
}
