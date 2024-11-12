package cn.good.domain.strategy.service;

import cn.good.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * TODO
 *
 * @Description 策略奖品
 * @Author wkm
 * @Date 2024/10/20
 **/
public interface IRaffleAward {
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);
    List<StrategyAwardEntity> queryRaffleStrategyAwardListByActivityId(Long activityId);
}
