package cn.good.domain.activity.service.armory;

/**
 * TODO
 *
 * @Description 活动装配预热
 * @Author wkm
 * @Date 2024/10/30
 **/
public interface IActivityArmory {
    boolean assembleActivitySkuByActivityId(Long activityId);
    boolean assembleActivitySku(Long sku);
}
