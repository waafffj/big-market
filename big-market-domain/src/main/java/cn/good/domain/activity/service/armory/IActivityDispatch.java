package cn.good.domain.activity.service.armory;

import java.util.Date;

/**
 * TODO
 *
 * @Description 活动调度【扣减库存】
 * @Author wkm
 * @Date 2024/10/30
 **/
public interface IActivityDispatch {
    /**
     * 根据策略ID和奖品ID 扣减奖品缓存库存
     * @param sku
     * @param endDateTime
     * @return  扣减结果
     */
    boolean subtractionActivitySkuStock(Long sku, Date endDateTime);
}
