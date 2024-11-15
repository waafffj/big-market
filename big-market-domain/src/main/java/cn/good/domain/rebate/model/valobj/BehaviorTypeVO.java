package cn.good.domain.rebate.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/15
 **/
@Getter
@AllArgsConstructor
public enum BehaviorTypeVO {
    SIGN("sign","签到(日历)"),
    OPENAI_PAY("openai_pay","openai 外部支付完成"),
    ;
    private final String code;
    private final String info;
}
