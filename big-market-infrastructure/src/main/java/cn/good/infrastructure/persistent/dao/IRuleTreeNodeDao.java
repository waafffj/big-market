package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @Description 规则树节点表DAO
 * @Author wkm
 * @Date 2024/10/15
 **/
@Mapper
public interface IRuleTreeNodeDao {
    List<RuleTreeNode> queryRuleTreeNodeListByTreeId(String treeId);
}
