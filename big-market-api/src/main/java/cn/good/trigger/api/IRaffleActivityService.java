package cn.good.trigger.api;

import cn.good.trigger.api.dto.*;
import cn.good.types.model.Response;

import java.math.BigDecimal;
import java.util.List;

/**
 * TODO
 *
 * @Description 抽奖活动服务
 * @Author wkm
 * @Date 2024/11/6
 **/
public interface IRaffleActivityService {
    /**
     * 活动装配，数据预热缓存
     * @param activityId
     * @return
     */
    Response<Boolean> armory(Long activityId);

    /**
     * 活动抽奖接口
     * @param request
     * @return
     */

    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);

    /**
     * 日历签到返利接口
     * @param userId
     * @return 签到结果
     */
    Response<Boolean> calendarSignRebate(String userId);


    /**
     * 判断是否完成日历签到返利接口
     *
     * @param userId 用户ID
     * @return 签到结果 true 已签到，false 未签到
     */

    Response<Boolean> isCalendarSignRebate(String userId);

    /**
     * 查询用户活动账户
     * @param request
     * @return
     */
    Response<UserActivityAccountResponseDTO> queryUserActivityAccount(UserActivityAccountRequestDTO request);

    /**
     * 查询sku商品集合
     * @param activityId
     * @return 商品集合
     */
    Response<List<SkuProductResponseDTO>> querySkuProductListByActivityId(Long activityId);

    /**
     * 查询用户积分值
     * @param userId
     * @return 可用积分
     */
    Response<BigDecimal> queryUserCreditAccount(String userId);

    /**
     * 积分支付兑换商品
     * @param request
     * @return
     */
    Response<Boolean> creditPayExchangeSku(SkuProductShopCartRequestDTO request);
}
