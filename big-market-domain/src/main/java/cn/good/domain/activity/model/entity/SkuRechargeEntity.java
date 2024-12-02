package cn.good.domain.activity.model.entity;

import cn.good.domain.activity.model.valobj.OrderTradeTypeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description  活动购物车实体对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuRechargeEntity {
    private String userId;
    /** 商品SKU - activity + activity count */
    private Long sku;
    /**
     *幂等业务单号，外部谁充值谁透传
     */
    private String outBusinessNo;
    private OrderTradeTypeVO orderTradeType = OrderTradeTypeVO.rebate_no_pay_trade;
}
