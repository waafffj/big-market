package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description  规则树节点
 * @Author wkm
 * @Date 2024/10/15
 **/
@Data
public class RuleTreeNode {
    private Long id;
    /* 规则树ID */
    private String treeId;
    private String ruleKey;
    private String ruleDesc;
    /* 规则比值 */
    private String ruleValue;
    private Date createTime;
    private Date updateTime;
}
