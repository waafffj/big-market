package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @Description  规则树表dao
 * @Author wkm
 * @Date 2024/10/15
 **/

@Mapper
public interface IRuleTreeDao {
    RuleTree queryRuleTreeByTreeId(String treeId);
}
