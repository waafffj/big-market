package cn.good.trigger.api.dto;

import lombok.Data;

/**
 * TODO
 *
 * @Description 商品购物车请求对象
 * @Author wkm
 * @Date 2024/12/2
 **/
@Data
public class SkuProductShopCartRequestDTO {
    private String userId;
    private Long sku;
}
