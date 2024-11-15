package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.DailyBehaviorRebate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @Description 日常行为返利活动配置
 * @Author wkm
 * @Date 2024/11/15
 **/
@Mapper
public interface IDailyBehaviorRebateDao {
    List<DailyBehaviorRebate> queryDailyBehaviorRebateByBehaviorType(String behaviorType);
}
