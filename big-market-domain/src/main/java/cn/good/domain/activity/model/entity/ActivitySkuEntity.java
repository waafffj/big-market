package cn.good.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description 活动sku实体对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuEntity {
    private Long sku;
    private Long activityId;
    /*  活动个人参数ID (在这个活动上，一个人可参与多少次活动 (总、月、日))*/
    private Long activityCountId;
    /*  库存总量    */
    private Integer stockCount;
    /*  剩余库存 */
    private Integer stockCountSurplus;
}
