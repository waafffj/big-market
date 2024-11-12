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
    Response<Boolean> armory(Long activityId);

    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);
}
