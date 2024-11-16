package cn.good.trigger.api;

import cn.good.trigger.api.dto.ActivityDrawRequestDTO;
import cn.good.trigger.api.dto.ActivityDrawResponseDTO;
import cn.good.types.model.Response;

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

}
