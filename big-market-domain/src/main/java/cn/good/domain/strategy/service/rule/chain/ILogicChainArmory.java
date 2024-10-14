package cn.good.domain.strategy.service.rule.chain;

public interface ILogicChainArmory  {
    ILogicChain next();
    ILogicChain appendNext(ILogicChain next);
}
