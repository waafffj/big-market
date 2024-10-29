package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.RaffleActivityAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @Description 抽奖活动账户表
 * @Author wkm
 * @Date 2024/10/28
 **/
@Mapper
public interface IRaffleActivityAccountDao {
    void insert(RaffleActivityAccount raffleActivityAccount);
    int updateAccountQuota(RaffleActivityAccount raffleActivityAccount);
}
