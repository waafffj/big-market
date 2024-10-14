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
public class RuleTreeNodeVO {
    private Integer treeId;
    private String ruleKey;
    private String ruleDesc;
    private String ruleValue;
    private List<RuleTreeNodeLineVO> treeNodeLineVOList;
}
