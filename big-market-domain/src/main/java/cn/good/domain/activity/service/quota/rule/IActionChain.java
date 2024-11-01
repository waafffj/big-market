package cn.good.domain.activity.service.quota.rule;

import cn.good.domain.activity.model.entity.ActivityCountEntity;
import cn.good.domain.activity.model.entity.ActivityEntity;
import cn.good.domain.activity.model.entity.ActivitySkuEntity;

/**
 * TODO
 *
 * @Description 下单过滤接口
 * @Author wkm
 * @Date 2024/10/29
 **/
public interface IActionChain extends IActionChainArmory{
    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
