package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description 规则树
 * @Author wkm
 * @Date 2024/10/15
 **/
@Data
public class RuleTree {
    /* 自增ID */
    private Long id;
    /*  规则树ID */
    private String treeId;
    private String treeName;
    private String treeDesc;
    /* 规则根节点 */
    private String treeRootRuleKey;
    private Date createTime;
    private Date updateTime;
}
