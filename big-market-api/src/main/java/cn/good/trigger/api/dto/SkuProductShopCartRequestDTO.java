package cn.good.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description 商品购物车请求对象
 * @Author wkm
 * @Date 2024/12/2
 **/
@Data
public class SkuProductShopCartRequestDTO implements Serializable {
    private String userId;
    private Long sku;
}
