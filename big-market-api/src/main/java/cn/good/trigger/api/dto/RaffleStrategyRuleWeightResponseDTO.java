package cn.good.trigger.api.dto;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @Description 抽奖策略规则，权重配置，查询N次抽奖可解锁奖品范围，应答对象
 * @Author wkm
 * @Date 2024/11/16
 **/
@Data
public class RaffleStrategyRuleWeightResponseDTO {
    // 权重规则配置的抽奖次数
    private Integer ruleWeightCount;
    private Integer userActivityAccountTotalUseCount;
    private List<StrategyAward> strategyAwards;
    @Data
    public static class StrategyAward{
        private Integer awardId;
        private String awardTitle;
    }
}
