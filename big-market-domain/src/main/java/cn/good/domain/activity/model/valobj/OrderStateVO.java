package cn.good.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @Description  订单状态枚举值对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Getter
@AllArgsConstructor
public enum OrderStateVO {
    wait_pay("wait_pay","待支付"),
    completed("completed","完成");
    private final String code;
    private final String desc;
}
