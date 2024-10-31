package cn.good.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description 活动sku库存 KEY 值对象
 * @Author wkm
 * @Date 2024/10/30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuStockKeyVO {
    private Long sku;
    private Long activityId;
}
