package cn.good.domain.strategy.service.armory;

public interface IStrategyArmory {
    boolean assembleLotteryStrategy(Long strategyId);  //装配动作
    boolean assembleLotteryStrategyByActivityId(Long activityId);

}
