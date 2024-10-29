package cn.good.domain.activity.model.entity;

import lombok.Data;

/**
 * TODO
 *
 * @Description  活动购物车实体对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data

public class SkuRechargeEntity {
    private String userId;
    /** 商品SKU - activity + activity count */
    private Long sku;
    /**
     *幂等业务单号，外部谁充值谁透传
     */
    private String outBusinessNo;
}
