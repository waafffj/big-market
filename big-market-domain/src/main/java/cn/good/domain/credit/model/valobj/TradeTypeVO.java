package cn.good.domain.credit.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @Description 交易类型枚举值
 * @Author wkm
 * @Date 2024/11/30
 **/
@Getter
@AllArgsConstructor
public enum TradeTypeVO {
    FORWARD("forward", "正向交易，+ 积分"),
    REVERSE("reverse", "逆向交易，- 积分"),
    ;
    private final String code;
    private final String info;
}
