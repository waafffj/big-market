package cn.good.domain.activity.service.quota;

import cn.good.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.good.domain.activity.model.entity.ActivityCountEntity;
import cn.good.domain.activity.model.entity.ActivityEntity;
import cn.good.domain.activity.model.entity.ActivitySkuEntity;
import cn.good.domain.activity.model.entity.SkuRechargeEntity;
import cn.good.domain.activity.repository.IActivityRepository;
import cn.good.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.good.domain.activity.service.quota.rule.IActionChain;
import cn.good.domain.activity.service.quota.rule.factory.DefaultActivityChainFactory;
import cn.good.types.enums.ResponseCode;
import cn.good.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @Description 抽象活动抽象类 定义标准的流程
 * @Author wkm
 * @Date 2024/10/28
 **/
@Slf4j
public abstract class AbstractRaffleActivityAccountQuota extends RaffleActivityAccountQuotaSupport implements IRaffleActivityAccountQuotaService {


 public AbstractRaffleActivityAccountQuota(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
  super(activityRepository,defaultActivityChainFactory);
 }

 @Override
 public String createOrder(SkuRechargeEntity skuRechargeEntity) {
  /* 参数校验 */
  String userId = skuRechargeEntity.getUserId();
  Long sku = skuRechargeEntity.getSku();
  String outBusinessNo = skuRechargeEntity.getOutBusinessNo();
  if(null == sku || StringUtils.isBlank(userId) || StringUtils.isBlank(outBusinessNo)){
   throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(),ResponseCode.ILLEGAL_PARAMETER.getInfo());
  }
/**
 * 查询基础信息
 * 通过sku查询活动信息
 *
 */
  ActivitySkuEntity activitySkuEntity = queryActivitySku(sku);
  /*  查询活动信息  */
  ActivityEntity activityEntity = queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
  /*  查询次数信息 */
  ActivityCountEntity activityCountEntity = queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());
  /*  活动规则校验*/
  IActionChain actionChain = defaultActivityChainFactory.openActionChain();
  actionChain.action(activitySkuEntity,activityEntity,activityCountEntity);
   /* 构建订单聚合对象 */
  CreateQuotaOrderAggregate createQuotaOrderAggregate = buildOrderAggregate(skuRechargeEntity,activitySkuEntity,activityEntity,activityCountEntity);
  /*  保存订单 */
  doSaveOrder(createQuotaOrderAggregate);
  /* 返回单号 */
  return createQuotaOrderAggregate.getActivityOrderEntity().getOrderId();
 }
 protected abstract CreateQuotaOrderAggregate buildOrderAggregate(SkuRechargeEntity skuRechargeEntity, ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
 protected abstract void doSaveOrder(CreateQuotaOrderAggregate createQuotaOrderAggregate);
}
