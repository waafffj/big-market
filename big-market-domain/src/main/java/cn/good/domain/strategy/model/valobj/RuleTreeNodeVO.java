package cn.good.domain.strategy.model.valobj;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeVO {  // 节点
    private String treeId;
    private String ruleKey;
    private String ruleDesc;
    private String ruleValue;
    /* 规则连线 */
    private List<RuleTreeNodeLineVO> treeNodeLineVOList;
}
