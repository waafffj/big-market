package cn.good.domain.strategy.service.rule.chain.impl;

import cn.good.domain.strategy.repository.IStrategyRepository;
import cn.good.domain.strategy.service.armory.IStrategyDispatch;
import cn.good.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.good.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.good.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Component("rule_weight")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RuleWeightLogicChain extends AbstractLogicChain {
    @Resource
    private IStrategyRepository repository;
    @Resource
    protected IStrategyDispatch strategyDispatch;

    /**
     * 权重责任链过滤；
     * 1. 权重规则格式；4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
     * 2. 解析数据格式；判断哪个范围符合用户的特定抽奖范围
     */
    @Override
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        log.info("抽奖责任链-权重开始 userId :{} strategyId :{} ruleModel :{}",userId,strategyId,ruleModel());

        String ruleValue = repository.queryStrategyRuleValue(strategyId,ruleModel());
        /* 4000 ,  "4000:102,103,104,105"*/
        Map<Long,String> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if(null == analyticalValueGroup || analyticalValueGroup.isEmpty()) {
            log.warn("抽奖责任链-权重告警【策略配置权重，但ruleValue未配置相应值】 userId :{} strategyId :{} ruleModel :{}",userId,strategyId,ruleModel());
            return next().logic(userId,strategyId);
        }
        List<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
        Collections.sort(analyticalSortedKeys);


        Integer userScore = repository.queryActivityAccountTotalUseCount(userId,strategyId);
        Long nextValue = analyticalSortedKeys.stream()
                .sorted(Comparator.reverseOrder())
                .filter(analyticalSortedKeyValue -> userScore >= analyticalSortedKeyValue)
                .findFirst()
                .orElse(null);

        if(null != nextValue){
            Integer awardId = strategyDispatch.getRandomAwardId(strategyId, analyticalValueGroup.get(nextValue));
            log.info("抽奖责任链-权重接管 userId :{} strategyId:{} ruleModel :{} awardId :{}",userId,strategyId,ruleModel(),awardId);
            return DefaultChainFactory.StrategyAwardVO.builder()
                    .awardId(awardId)
                    .logicModel(ruleModel())
                    .build();
        }

        // 5. 过滤其他责任链

        log.info("抽奖责任链-权重放行 userId :{} strategyId :{} ruleModel:{}",userId,strategyId,ruleModel());
        return next().logic(userId,strategyId);
    }
       /* 返回类型比如 4000 ,  "4000:102,103,104,105"    */
    private Map<Long, String> getAnalyticalValue(String ruleValue) {
        String [] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<Long,String> ruleValueMap = new HashMap<>();
        for(String ruleValueKey : ruleValueGroups){
            if(ruleValueKey == null || ruleValueKey.isEmpty()){
                return ruleValueMap;
            }
            String [] parts = ruleValueKey.split(Constants.COLON);
            if(parts.length != 2){
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueKey);
            }
            ruleValueMap.put(Long.parseLong(parts[0]),ruleValueKey);
        }
        return ruleValueMap;
    }

    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_WEIGHT.getCode();
    }

}
