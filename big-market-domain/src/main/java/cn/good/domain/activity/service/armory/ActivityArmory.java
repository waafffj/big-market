package cn.good.domain.activity.service.armory;

import cn.good.domain.activity.model.entity.ActivitySkuEntity;
import cn.good.domain.activity.repository.IActivityRepository;
import cn.good.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/10/30
 **/
@Slf4j
@Service
public class ActivityArmory implements IActivityArmory,IActivityDispatch{
    @Resource
    private IActivityRepository activityRepository;

    @Override
    public boolean assembleActivitySku(Long sku) {
        ActivitySkuEntity activitySkuEntity = activityRepository.queryActivitySku(sku);
        cacheActivitySkuStockCount(sku,activitySkuEntity.getStockCount());

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

    @Override
    public boolean subtractionActivitySkuStock(Long sku, Date endDateTime) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_STOCK_COUNT_KEY + sku;
        return activityRepository.subtractionActivitySkuStock(sku,cacheKey,endDateTime);
    }
}
