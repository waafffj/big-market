package cn.good.domain.strategy.service;

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
}
