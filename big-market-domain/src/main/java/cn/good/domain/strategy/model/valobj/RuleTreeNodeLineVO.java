package cn.good.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeLineVO {
    private Integer treeId;
    private String ruleNodeFrom;

    private String ruleNodeTo;

    private RuleLimitTypeVO ruleLimitType;

    private RuleLogicCheckTypeVO ruleLimitValue;
}
