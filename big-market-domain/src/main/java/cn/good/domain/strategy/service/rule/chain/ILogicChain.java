package cn.good.domain.strategy.service.rule.chain;

import cn.good.domain.strategy.service.rule.chain.factory.DefaultChainFactory;

public interface ILogicChain extends ILogicChainArmory{
    DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);
}
