package cn.good.domain.award.repository;

import cn.good.domain.award.model.aggregate.GiveOutPrizesAggregate;
import cn.good.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/4
 **/
public interface IAwardRepository {
    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);

    String queryAwardConfig(Integer awardId);

    void saveGiveOutPrizesAggregate(GiveOutPrizesAggregate giveOutPrizesAggregate);

    String queryAwardKey(Integer awardId);
}
