package cn.good.domain.strategy.service;

import cn.good.domain.strategy.model.entity.RaffleAwardEntity;
import cn.good.domain.strategy.model.entity.RaffleFactorEntity;

public interface IRaffleStrategy {
    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);
}
