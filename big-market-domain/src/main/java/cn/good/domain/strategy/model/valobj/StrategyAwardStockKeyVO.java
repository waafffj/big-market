package cn.good.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description 策略奖品库存Key标识值对象
 * @Author wkm
 * @Date 2024/10/16
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {
    private Long strategyId;
    private Integer awardId;
}
