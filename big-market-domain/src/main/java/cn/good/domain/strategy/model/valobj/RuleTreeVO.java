package cn.good.domain.strategy.model.valobj;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeVO {  // 树根

    /*  规则树ID  */
    private String treeId;

    private String treeName;

    private String treeDesc;
    /*  规则根节点  */
    private String treeRootRuleNode;
    /* 规则节点   */
    private Map<String,RuleTreeNodeVO> treeNodeMap;
}
