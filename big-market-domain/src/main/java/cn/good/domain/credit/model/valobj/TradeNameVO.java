package cn.good.domain.credit.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @Description 交易名称枚举值
 * @Author wkm
 * @Date 2024/11/30
 **/
@Getter
@AllArgsConstructor
public enum TradeNameVO {
    REBATE("行为返利"),
    CONVERT_SKU("兑换抽奖"),
    ;
    private final String name;
}
