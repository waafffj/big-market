package cn.good.domain.award.service;

import cn.good.domain.award.model.entity.DistributeAwardEntity;
import cn.good.domain.award.model.entity.UserAwardRecordEntity;

/**
 * TODO
 *
 * @Description 奖品服务接口
 * @Author wkm
 * @Date 2024/11/4
 **/
public interface IAwardService  {
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);
    /* 配送发货奖品 */
    void distributeAward(DistributeAwardEntity distributeAwardEntity);
}
