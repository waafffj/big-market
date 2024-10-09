package cn.good.domain.strategy.service.rule.impl;

import cn.good.domain.strategy.model.entity.RuleActionEntity;
import cn.good.domain.strategy.model.entity.RuleMatterEntity;
import cn.good.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.good.domain.strategy.repository.IStrategyRepository;
import cn.good.domain.strategy.service.annotation.LogicStrategy;
import cn.good.domain.strategy.service.rule.ILogicFilter;
import cn.good.domain.strategy.service.rule.factory.DefaultLogicFactory;
import cn.good.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_WIGHT)
public class RuleWeightLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepository repository;
    public Long userScore = 4500L;


    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-权重范围 userId:{} strategyId:{} ruleModel:{}",ruleMatterEntity.getUserId(),ruleMatterEntity.getStrategyId(),ruleMatterEntity.getRuleModel());
        String userId = ruleMatterEntity.getUserId();
        Long strategyId = ruleMatterEntity.getStrategyId();
        String ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(),ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());

        Map<Long,String> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if(null == analyticalValueGroup || analyticalValueGroup.isEmpty()){
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        List<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
        Collections.sort(analyticalSortedKeys);

        Long nextValue = analyticalSortedKeys.stream()
                .filter(key -> userScore >= key)
                .findFirst()
                .orElse(null);

        if(null != nextValue){
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .data(RuleActionEntity.RaffleBeforeEntity.builder()
                            .strategyId(strategyId)
                            .ruleWeightValueKey(analyticalValueGroup.get(nextValue))
                            .build())
                    .ruleModel(DefaultLogicFactory.LogicModel.RULE_WIGHT.getCode())
                    .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                    .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                    .build();
        }
        return  RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }
    private Map<Long,String> getAnalyticalValue(String ruleValue){
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<Long,String> ruleValueMap = new HashMap<>();
        for(String ruleValuekey : ruleValueGroups){
            if(ruleValuekey == null || ruleValuekey.isEmpty()){
                return ruleValueMap;
            }
            String [] parts = ruleValuekey.split(Constants.COLON);
            if(parts.length != 2){
                throw new IllegalArgumentException("rule_weight rule_rule invaild input format" + ruleValuekey);
            }
            ruleValueMap.put(Long.parseLong(parts[0]),ruleValuekey);
        }
        return ruleValueMap;
    }
}
