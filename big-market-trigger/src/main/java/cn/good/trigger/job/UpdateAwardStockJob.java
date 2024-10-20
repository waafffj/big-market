package cn.good.trigger.job;

import cn.good.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.good.domain.strategy.service.IRaffleStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/10/16
 **/

@Slf4j
@Component()
public class UpdateAwardStockJob {
    @Resource
    private IRaffleStock raffleStock;
    @Scheduled(cron = "0/5 * * * * ?")
    public void exec(){
        try{
            log.info("定时任务,更新奖品消耗库存【延迟队列获取，降低对数据库的更新频次】");
            StrategyAwardStockKeyVO strategyAwardStockKeyVO = raffleStock.takeQueueValue();
            if(null == strategyAwardStockKeyVO) return;
            log.info("定时任务，更新奖品消耗 strategyId :{} awardId :{}",strategyAwardStockKeyVO.getStrategyId(),strategyAwardStockKeyVO.getAwardId());
            raffleStock.updateStrategyAwardStock(strategyAwardStockKeyVO.getStrategyId(),strategyAwardStockKeyVO.getAwardId());
        }catch (Exception e){
            log.error("定时任务，更新奖品消耗库存失败",e);
        }
    }
}
