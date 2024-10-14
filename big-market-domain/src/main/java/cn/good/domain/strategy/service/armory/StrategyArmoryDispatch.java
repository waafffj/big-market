package cn.good.domain.strategy.service.armory;

import cn.good.domain.strategy.model.entity.StrategyAwardEntity;
import cn.good.domain.strategy.model.entity.StrategyEntity;
import cn.good.domain.strategy.model.entity.StrategyRuleEntity;
import cn.good.domain.strategy.repository.IStrategyRepository;
import cn.good.types.common.Constants;
import cn.good.types.enums.ResponseCode;
import cn.good.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

@Slf4j
@Service
public class StrategyArmoryDispatch implements IStrategyArmory,IStrategyDispatch{


    @Resource
    private IStrategyRepository  repository;
    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);
        assembleLotteryStrategy(String.valueOf(strategyId),strategyAwardEntities);

        StrategyEntity strategyEntity = repository.queryStrategyEntityByStrategyId(strategyId);
        String ruleWeight = strategyEntity.getRuleWeight();
        if(null == ruleWeight) return true;

        StrategyRuleEntity strategyRuleEntity = repository.queryStrategyRule(strategyId,ruleWeight);

        if(null == strategyRuleEntity ){
            throw new AppException(ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode(),ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getInfo());
        }

        Map<String,List<Integer>> ruleWeightValueMap = strategyRuleEntity.getRuleWeightValues();
        Set<String> keys = ruleWeightValueMap.keySet();
        for(String key : keys){
            List<Integer> ruleWeightValues = ruleWeightValueMap.get(key);
            ArrayList<StrategyAwardEntity> strategyAwardEntitiesClone = new ArrayList<>(strategyAwardEntities);
            strategyAwardEntitiesClone.removeIf(entity -> !ruleWeightValues.contains(entity.getAwardId()));
            assembleLotteryStrategy(String.valueOf(strategyId).concat(Constants.UNDERLINE).concat(key),strategyAwardEntitiesClone);
        }
        return true;
    }

    private void assembleLotteryStrategy(String key,List<StrategyAwardEntity> strategyAwardEntities){
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        BigDecimal rateRange = totalAwardRate.divide(minAwardRate,0, RoundingMode.CEILING);
        List<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());
        for(StrategyAwardEntity strategyAward : strategyAwardEntities){
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            for(int i = 0;i < rateRange.multiply(awardRate).setScale(0,RoundingMode.CEILING).intValue();i ++ ){
                strategyAwardSearchRateTables.add(awardId);
            }
        }

        Collections.shuffle(strategyAwardSearchRateTables);

        Map<Integer,Integer> shuffleStrategyAwardSearchRateTable = new LinkedHashMap<>();
        for(int i = 0;i < strategyAwardSearchRateTables.size();i ++ ){
            shuffleStrategyAwardSearchRateTable.put(i,strategyAwardSearchRateTables.get(i));
        }

        repository.storeStrategyAwardSearchRateTable(key,shuffleStrategyAwardSearchRateTable.size(),shuffleStrategyAwardSearchRateTable);
    }

    private double convert(double min){
        double current = min;
        double max = 1;
        while (current < 1){
            current = current * 10;
            max = max * 10;
        }
        return max;
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);
        return repository.getStrategyAwardAssemble(String.valueOf(strategyId),new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Integer getRandomAwardId(Long strategyId, String ruleWeightValue) {
        String key = String.valueOf(strategyId).concat(Constants.UNDERLINE).concat(ruleWeightValue);
        return getRandomAwardId(key);
    }

    @Override
    public Integer getRandomAwardId(String key) {
        int rateRange = repository.getRateRange(key);
        return repository.getStrategyAwardAssemble(key,new SecureRandom().nextInt(rateRange));
    }
}
