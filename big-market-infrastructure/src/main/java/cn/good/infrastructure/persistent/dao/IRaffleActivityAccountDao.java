package cn.good.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
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
    @DBRouter
    RaffleActivityAccount queryActivityAccountByUserId(RaffleActivityAccount raffleActivityAccountReq);
    int updateActivityAccountSubtractionQuota(RaffleActivityAccount raffleActivityAccount);
    int updateActivityAccountMonthSubtractionQuota(RaffleActivityAccount raffleActivityAccount);
    int updateActivityAccountDaySubtractionQuota(RaffleActivityAccount raffleActivityAccount);
    void updateActivityAccountMonthSurplusImageQuota(RaffleActivityAccount raffleActivityAccount);
    void updateActivityAccountDaySurplusImageQuota(RaffleActivityAccount raffleActivityAccount);
}
