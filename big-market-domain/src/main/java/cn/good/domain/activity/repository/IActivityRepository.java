package cn.good.domain.activity.repository;

import cn.good.domain.activity.model.aggregate.CreateOrderAggregate;
import cn.good.domain.activity.model.entity.ActivitySkuEntity;
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
    ActivitySkuEntity queryActivitySku(Long sku);
    ActivityEntity queryRaffleActivityByActivityId(Long activityId);
    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);
    void doSaveOrder(CreateOrderAggregate createOrderAggregate);
}
