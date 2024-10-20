package cn.good.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author wkm
 * @description 规则树节点指向线对象。用于衔接 from->to 节点链路关系
 * @create 2024-10-15
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeLineVO {/*  连线   */
    /*  规则树ID  */
    private String treeId;
    private String ruleNodeFrom;

    private String ruleNodeTo;

    private RuleLimitTypeVO ruleLimitType;
    /*  限定值(到下个节点)  */
    private RuleLogicCheckTypeVO ruleLimitValue;
}
