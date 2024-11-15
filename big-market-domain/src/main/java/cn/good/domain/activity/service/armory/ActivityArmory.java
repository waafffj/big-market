package cn.good.domain.activity.service.armory;

import cn.good.domain.activity.model.entity.ActivitySkuEntity;
import cn.good.domain.activity.repository.IActivityRepository;
import cn.good.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @Description  活动sku装配
 * @Author wkm
 * @Date 2024/10/30
 **/
@Slf4j
@Service
public class ActivityArmory implements IActivityArmory,IActivityDispatch{
    @Resource
    private IActivityRepository activityRepository;

    @Override
    public boolean assembleActivitySkuByActivityId(Long activityId) {
        List<ActivitySkuEntity> activitySkuEntities = activityRepository.queryActivitySkuListByActivityId(activityId);
        for(ActivitySkuEntity activitySkuEntity : activitySkuEntities){
            cacheActivitySkuStockCount(activitySkuEntity.getSku(),activitySkuEntity.getStockCountSurplus());
            activityRepository.queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());
        }
        activityRepository.queryRaffleActivityByActivityId(activityId);
        return true;
    }

    @Override
    public boolean assembleActivitySku(Long sku) {
        ActivitySkuEntity activitySkuEntity = activityRepository.queryActivitySku(sku);
        /*  缓存库存 */
        cacheActivitySkuStockCount(sku,activitySkuEntity.getStockCountSurplus());

        /* 预热活动 【查询时预热到缓存】*/
        activityRepository.queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        /*  预热活动次数【查询时预热到缓存】*/
        activityRepository.queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());
        return true;
    }
    private void cacheActivitySkuStockCount(Long sku,Integer stockCount){
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_STOCK_COUNT_KEY + sku;
        activityRepository.cacheActivitySkuStockCount(cacheKey,stockCount);
    }


    /*   库存扣减 */

    /**
     *
     * @param sku
     * @param endDateTime 活动结束时间
     * @return
     */
    @Override
    public boolean subtractionActivitySkuStock(Long sku, Date endDateTime) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_STOCK_COUNT_KEY + sku;
        return activityRepository.subtractionActivitySkuStock(sku,cacheKey,endDateTime);
    }
}
