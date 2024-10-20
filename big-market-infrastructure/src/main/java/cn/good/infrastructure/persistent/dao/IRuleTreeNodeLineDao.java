package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @Description  规则树节点连线表DAO
 * @Author wkm
 * @Date 2024/10/15
 **/

@Mapper
public interface IRuleTreeNodeLineDao {
    List<RuleTreeNodeLine> queryRuleTreeNodeLineListByTreeId(String treeId);
}
