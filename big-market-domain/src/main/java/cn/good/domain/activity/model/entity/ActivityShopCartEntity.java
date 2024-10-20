package cn.good.domain.activity.model.entity;

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

public class ActivityShopCartEntity {
    private String userId;
    /** 商品SKU - activity + activity count */
    private Long sku;
}
