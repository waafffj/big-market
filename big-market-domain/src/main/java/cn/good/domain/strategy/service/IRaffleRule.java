package cn.good.domain.strategy.service;

import cn.good.domain.strategy.model.valobj.RuleWeightVO;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/6
 **/
public interface IRaffleRule {
    /**
     * 根据规则树ID集合查询奖品中加锁数量的配置[部分奖品需要抽奖N次解锁]
     * @param treeIds 规则树id
     * @return RULE_lock  加锁值
     */
    Map<String,Integer> queryAwardRuleLockCount(String[] treeIds);
    /**
     * 查询奖品权重配置
     *
     * @param strategyId 策略ID
     * @return 权重规则
     */

    List<RuleWeightVO> queryAwardRuleWeight(Long strategyId);
    /**
     * 查询奖品权重配置
     *
     * @param activityId 活动ID
     * @return 权重规则
     */

    List<RuleWeightVO> queryAwardRuleWeightByActivityId(Long activityId);
}
