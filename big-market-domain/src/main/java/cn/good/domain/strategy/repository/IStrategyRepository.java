package cn.good.domain.strategy.repository;

import cn.good.domain.strategy.model.entity.StrategyAwardEntity;
import cn.good.domain.strategy.model.entity.StrategyEntity;
import cn.good.domain.strategy.model.entity.StrategyRuleEntity;
import cn.good.domain.strategy.model.valobj.StrategyAwardRuleModelVO;

import java.util.List;
import java.util.Map;

public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);
    void storeStrategyAwardSearchRateTable(String key, Integer rateRange, Map<Integer,Integer> strategyAwardSearchRateTable);
    Integer getStrategyAwardAssemble(String key,Integer rateKey);
    int getRateRange(Long strategyId);
    int getRateRange(String key);
    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);
    StrategyRuleEntity queryStrategyRule(Long strategyId,String ruleModel);
    String queryStrategyRuleValue(Long strategyId,String ruleModel);
    String queryStrategyRuleValue(Long strategyId,Integer awardId,String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId);
}
