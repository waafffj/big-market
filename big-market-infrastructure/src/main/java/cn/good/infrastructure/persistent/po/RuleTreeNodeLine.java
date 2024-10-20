package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description  规则树节点线 from -> to
 * @Author wkm
 * @Date 2024/10/15
 **/

@Data
public class RuleTreeNodeLine {
    private Long id;
    /* 规则树ID */
    private String treeId;
    /* 规则KEY节点 from */
    private String ruleNodeFrom;
    private String ruleNodeTo;
    /* 限定类型 1:= 2:>;*/
    private String ruleLimitType;
    /* 限定值(到下个节点) */
    private String ruleLimitValue;
    private Date createTime;
    private Date updateTime;
}
