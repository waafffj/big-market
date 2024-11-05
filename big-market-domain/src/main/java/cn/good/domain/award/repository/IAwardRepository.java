package cn.good.domain.award.repository;

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
}
