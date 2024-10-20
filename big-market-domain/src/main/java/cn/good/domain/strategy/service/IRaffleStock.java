package cn.good.domain.strategy.service;

import cn.good.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

/**
 * TODO
 *
 * @Description  抽奖库存相关服务 获取库存消耗队列
 * @Author wkm
 * @Date 2024/10/16
 **/
public interface IRaffleStock {
    /**
     * 获取奖品库存消耗队列
     *
     * @return 奖品库存Key信息
     * @throws InterruptedException 异常
     */

    StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException;

    /**
     * 更新奖品库存消耗记录
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     */

    void updateStrategyAwardStock(Long strategyId,Integer awardId);
}
