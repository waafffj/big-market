package cn.good.domain.strategy.service.rule.tree.factory;


import cn.good.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.good.domain.strategy.model.valobj.RuleTreeVO;
import cn.good.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.good.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import cn.good.domain.strategy.service.rule.tree.factory.engine.impl.DecisionTreeEngine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DefaultTreeFactory {
    private final Map<String,ILogicTreeNode> logicTreeNodeGroup;

    public DefaultTreeFactory(Map<String,ILogicTreeNode> logicTreeNodeGroup) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
    }
    public IDecisionTreeEngine openLogicTree(RuleTreeVO ruleTreeVO){
        return new DecisionTreeEngine(logicTreeNodeGroup,ruleTreeVO);
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeActionEntity{
        private RuleLogicCheckTypeVO ruleLogicCheckType;
        private StrategyAwardData strategyAwardData;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardData{
        private Integer awardId;
        private String awardRuleValue;
    }
}
