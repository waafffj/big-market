package cn.good.domain.credit.repository;

import cn.good.domain.credit.model.aggregate.TradeAggregate;
import cn.good.domain.credit.model.entity.CreditAccountEntity;

/**
 * TODO
 *
 * @Description 用户积分仓储
 * @Author wkm
 * @Date 2024/11/30
 **/
public interface ICreditRepository {
    void saveUserCreditTradeOrder(TradeAggregate tradeAggregate);

    CreditAccountEntity queryUserCreditAccount(String userId);
}
