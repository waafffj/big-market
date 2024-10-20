package cn.good.domain.activity.repository;

import cn.good.domain.activity.model.entity.ActitySkuEntity;
import cn.good.domain.activity.model.entity.ActivityCountEntity;
import cn.good.domain.activity.model.entity.ActivityEntity;

/**
 * TODO
 *
 * @Description 活动仓储接口
 * @Author wkm
 * @Date 2024/10/28
 **/
public interface IActivityRepository {
    ActitySkuEntity queryActivitySku(Long sku);
    ActivityEntity queryRaffleActivityByActivityId(Long activityId);
    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);
}
