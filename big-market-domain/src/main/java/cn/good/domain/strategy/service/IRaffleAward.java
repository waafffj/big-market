package cn.good.domain.strategy.service;

import cn.good.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/10/20
 **/
public interface IRaffleAward {
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);
}
