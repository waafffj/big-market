package cn.good.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @Description sku商品实体对象
 * @Author wkm
 * @Date 2024/12/2
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuProductEntity {
    private Long sku;
    private Long activityId;
    /**
     * 活动个人参与次数ID
     */
    private Long activityCountId;
    private Integer stockCount;
    private Integer stockCountSurplus;
    private BigDecimal productAmount;
    /**
     * 活动配置的次数 - 购买商品后可以获得的次数
     */
    private ActivityCount activityCount;
    @Data
    public static class ActivityCount{
        private Integer totalCount;
        private Integer dayCount;
        private Integer monthCount;
    }
}
