package cn.good.domain.activity.service;

import cn.good.domain.activity.model.entity.*;
import cn.good.domain.activity.repository.IActivityRepository;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @Description 抽象活动抽象类 定义标准的流程
 * @Author wkm
 * @Date 2024/10/28
 **/
@Slf4j
public abstract class AbstractRaffleActivity implements IRaffleOrder{

 protected IActivityRepository activityRepository;

 public AbstractRaffleActivity(IActivityRepository activityRepository) {
  this.activityRepository = activityRepository;

 }

 @Override
 public ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity) {
  ActitySkuEntity actitySkuEntity = activityRepository.queryActivitySku(activityShopCartEntity.getSku());
  ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(actitySkuEntity.getActivityId());
  ActivityCountEntity activityCountEntity = activityRepository.queryRaffleActivityCountByActivityCountId(actitySkuEntity.getActivityCountId());
  log.info("查询结果：{} {} {}", JSON.toJSONString(actitySkuEntity),JSON.toJSONString(activityEntity),JSON.toJSONString(activityCountEntity));
  return ActivityOrderEntity.builder().build();
 }
}
