package cn.good.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @Description 未完成支付订单
 * @Author wkm
 * @Date 2024/12/2
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnpaidActivityOrderEntity {
    private String userId;
    private String orderId;
    private String outBusinessNo;
    private BigDecimal payAmount;
}
