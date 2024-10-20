package cn.good.infrastructure.persistent.repository;

import cn.good.domain.activity.model.entity.ActitySkuEntity;
import cn.good.domain.activity.model.entity.ActivityCountEntity;
import cn.good.domain.activity.model.entity.ActivityEntity;
import cn.good.domain.activity.model.valobj.ActivityStateVO;
import cn.good.domain.activity.repository.IActivityRepository;
import cn.good.infrastructure.persistent.dao.IRaffleActivityCountDao;
import cn.good.infrastructure.persistent.dao.IRaffleActivityDao;
import cn.good.infrastructure.persistent.dao.IRaffleActivitySkuDao;
import cn.good.infrastructure.persistent.po.RaffleActivity;
import cn.good.infrastructure.persistent.po.RaffleActivityCount;
import cn.good.infrastructure.persistent.po.RaffleActivitySku;
import cn.good.infrastructure.persistent.redis.IRedisService;
import cn.good.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/10/28
 **/
@Repository
public class ActivityRepository implements IActivityRepository {
    @Resource
    private IRedisService redisService;
    @Resource
    private IRaffleActivityDao raffleActivityDao;
    @Resource
    private IRaffleActivitySkuDao raffleActivitySkuDao;
    @Resource
    private IRaffleActivityCountDao raffleActivityCountDao;
    @Override
    public ActitySkuEntity queryActivitySku(Long sku) {
        RaffleActivitySku raffleActivitySku = raffleActivitySkuDao.queryActivitySku(sku);
        return ActitySkuEntity.builder()
                .sku(raffleActivitySku.getSku())
                .activityId(raffleActivitySku.getActivityId())
                .activityCountId(raffleActivitySku.getActivityCountId())
                .stockCount(raffleActivitySku.getStockCount())
                .stockCountSurplus(raffleActivitySku.getStockCountSurplus())
                .build();
    }

    @Override
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        String cacheKey = Constants.RedisKey.ACTIVITY_KEY + activityId;
        ActivityEntity activityEntity = redisService.getValue(cacheKey);
        if(null != activityEntity) return activityEntity;

        RaffleActivity raffleActivity = raffleActivityDao.queryRaffleActivityByActivityId(activityId);
        activityEntity = ActivityEntity.builder()
                .activityId(raffleActivity.getActivityId())
                .activityName(raffleActivity.getActivityName())
                .activityDesc(raffleActivity.getActivityDesc())
                .beginDateTime(raffleActivity.getBeginDateTime())
                .endDateTime(raffleActivity.getEndDateTime())
                .strategyId(raffleActivity.getStrategyId())
                .state(ActivityStateVO.valueOf(raffleActivity.getState()))
                .build();
        redisService.setValue(cacheKey,activityEntity);
        return activityEntity;
    }

    @Override
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        String cacheKey = Constants.RedisKey.ACTIVITY_COUNT_KEY + activityCountId;
        ActivityCountEntity activityCountEntity = redisService.getValue(cacheKey);
        if(null != activityCountEntity) return activityCountEntity;

        RaffleActivityCount raffleActivityCount = raffleActivityCountDao.queryRaffleActivityCountByActivityCountId(activityCountId);
        activityCountEntity = ActivityCountEntity.builder()
                .activityCountId(raffleActivityCount.getActivityCountId())
                .totalCount(raffleActivityCount.getTotalCount())
                .dayCount(raffleActivityCount.getDayCount())
                .monthCount(raffleActivityCount.getMonthCount())
                .build();
        redisService.setValue(cacheKey,activityCountEntity);
        return activityCountEntity;
    }
}
