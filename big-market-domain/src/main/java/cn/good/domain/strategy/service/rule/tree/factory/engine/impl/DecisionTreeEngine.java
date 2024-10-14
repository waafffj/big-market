package cn.good.domain.strategy.service.rule.tree.factory.engine.impl;

import cn.good.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.good.domain.strategy.model.valobj.RuleTreeNodeLineVO;
import cn.good.domain.strategy.model.valobj.RuleTreeNodeVO;
import cn.good.domain.strategy.model.valobj.RuleTreeVO;
import cn.good.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.good.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.good.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class DecisionTreeEngine implements IDecisionTreeEngine {

    private final Map<String,ILogicTreeNode> logicTreeNodeGroup;

    private final RuleTreeVO ruleTreeVO;

    public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeNodeGroup, RuleTreeVO ruleTreeVO) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
        this.ruleTreeVO = ruleTreeVO;
    }


    @Override
    public DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId) {
        DefaultTreeFactory.StrategyAwardData strategyAwardData = null;

        String nextnode = ruleTreeVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeVOMap = ruleTreeVO.getTreeNodeMap();
        RuleTreeNodeVO ruleTreeNode = treeNodeVOMap.get(nextnode);

        while(null != nextnode){
            ILogicTreeNode logicTreeNode = logicTreeNodeGroup.get(ruleTreeNode.getRuleKey());
            DefaultTreeFactory.TreeActionEntity logicEntity = logicTreeNode.logic(userId,strategyId,awardId);
            RuleLogicCheckTypeVO ruleLogicCheckTypeVO = logicEntity.getRuleLogicCheckType();
            strategyAwardData = logicEntity.getStrategyAwardData();
            log.info("决策树引擎【{}】 treeId :{} node :{} code :{}",ruleTreeVO.getTreeName(),ruleTreeVO.getTreeId(),nextnode,ruleLogicCheckTypeVO.getCode());
            nextnode = nextnode(ruleLogicCheckTypeVO.getCode(),ruleTreeNode.getTreeNodeLineVOList());
            ruleTreeNode = treeNodeVOMap.get(nextnode);
        }
        return strategyAwardData;
    }

    public String nextnode(String matterValue, List<RuleTreeNodeLineVO> treeNodeLineVOList){
        if(null == treeNodeLineVOList || treeNodeLineVOList.isEmpty()) return null;
        for(RuleTreeNodeLineVO nodeLine : treeNodeLineVOList){
            if(decisionLogic(matterValue,nodeLine)){
                return nodeLine.getRuleNodeTo();
            }
        }
        throw new RuntimeException("决策树引擎,nextnode 计算失败 未找到可执行的节点");
    }
    public boolean decisionLogic(String matterValue,RuleTreeNodeLineVO nodeLine){
        switch (nodeLine.getRuleLimitType()){
            case EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue().getCode());
            case GT:
            case LT:
            case GE:
            case LE:
            default:
                return false;
        }
    }
}
