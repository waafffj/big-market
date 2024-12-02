package cn.good.domain.strategy.service.rule.tree.impl;

import cn.good.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.good.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.good.domain.strategy.repository.IStrategyRepository;
import cn.good.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.good.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.good.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component("rule_luck_award")
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {
    @Resource
    private IStrategyRepository strategyRepository;
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue, Date endDateTime) {
        log.info("规则过滤-兜底奖品 userId :{} strategyId :{} awardId :{} ruleValue :{}",userId,strategyId,awardId,ruleValue);
        String[] split = ruleValue.split(Constants.COLON);
        if(split.length == 0 ){
            log.info("规则过滤-兜底奖品，兜底奖品未配置告警 userId :{} strategyId :{} awardId :{}",userId,strategyId,awardId);
            throw new RuntimeException("兜底奖品未配置"+ ruleValue);
        }
        // 兜底奖励配置
        Integer luckAwardId = Integer.valueOf(split[0]);
        String awardRuleValue = split.length > 1 ? split[1] : "";
        /* 写入延迟队列，延迟消费更新数据库记录【在trigger的job;UpdateAwardStockJob 下消费队列】*/
        strategyRepository.awardStockConsumeSendQueue(StrategyAwardStockKeyVO.builder()
                .strategyId(strategyId)
                .awardId(luckAwardId)
                .build());
        /* 返回兜底奖励 */
        log.info("规则过滤-兜底奖品 userId :{} strategyId :{} awardId :{} awardRuleValue :{}",userId,strategyId,luckAwardId,awardRuleValue);
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                        .awardId(luckAwardId)
                        .awardRuleValue(awardRuleValue)
                        .build())
                .build();
    }
}
