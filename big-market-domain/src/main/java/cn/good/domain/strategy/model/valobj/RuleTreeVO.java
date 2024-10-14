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
public class RuleTreeVO {
    private Integer treeId;

    private String treeName;

    private String treeDesc;

    private String treeRootRuleNode;

    private Map<String,RuleTreeNodeVO> treeNodeMap;
}
