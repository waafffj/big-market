package cn.good.domain.activity.service;

import cn.good.domain.activity.model.entity.*;

import java.util.List;

/**
 * TODO
 *
 * @Description 抽奖活动账户额度服务
 * @Author wkm
 * @Date 2024/10/28
 **/
public interface IRaffleActivityAccountQuotaService {

    /**
     * 以sku创建抽奖活动订单，获得参与抽奖资格（可消耗的次数）
     *
     * @param skuRechargeEntity 活动sku实体，通过sku领取活动。
     * @return 未支付订单
     */

    UnpaidActivityOrderEntity createOrder(SkuRechargeEntity skuRechargeEntity);
    /**
     * 订单出货 - 积分充值
     * @param deliveryOrderEntity 出货单实体对象
     */
    void updateOrder(DeliveryOrderEntity deliveryOrderEntity);

    /**
     * 查询活动账户
     * @param activityId
     * @param userId
     * @return
     */
    Integer queryRaffleActivityAccountPartakeCount(Long activityId,String userId);
    /**
     * 查询活动账户 - 日，参与次数
     *
     * @param activityId 活动ID
     * @param userId     用户ID
     * @return 参与次数
     */
    Integer queryRaffleActivityAccountDayPartakeCount(Long activityId,String userId);


    /**
     * 查询活动账户额度
     * @param activityId
     * @param userId
     * @return
     */
    ActivityAccountEntity queryActivityAccountEntity(Long activityId,String userId);
    List<UserAwardRecordEntity> queryUserRecordEntity(String userId);
}
