package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.RaffleActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @Description 抽奖活动表Dao
 * @Author wkm
 * @Date 2024/10/28
 **/
@Mapper
public interface IRaffleActivityDao {
    RaffleActivity queryRaffleActivityByActivityId(Long activityId);
    Long queryStrategyIdByActivityId(Long activityId);
    Long queryActivityIdByStrategyId(Long strategyId);
}
