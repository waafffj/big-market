package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.RaffleActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @Description 抽奖活动次数配置表Dao
 * @Author wkm
 * @Date 2024/10/28
 **/
@Mapper
public interface IRaffleActivityCountDao {
    RaffleActivityCount queryRaffleActivityCountByActivityCountId(Long activityCountId);
}
