package cn.good.domain.strategy.service.rule;

import cn.good.domain.strategy.model.entity.RuleActionEntity;
import cn.good.domain.strategy.model.entity.RuleMatterEntity;


/**
 * @description 抽奖规则过滤接口
 */

public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {
    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
