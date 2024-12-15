package cn.good.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description 抽奖策略规则，权重配置，查询N次抽奖可解锁奖品范围，请求对象
 * @Author wkm
 * @Date 2024/11/16
 **/
@Data
public class RaffleStrategyRuleWeightRequestDTO implements Serializable {
    private String userId;
    private Long activityId;
}
