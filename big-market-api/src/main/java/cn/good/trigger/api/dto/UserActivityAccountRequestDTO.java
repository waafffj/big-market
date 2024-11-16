package cn.good.trigger.api.dto;

import lombok.Data;

/**
 * TODO
 *
 * @Description 用户活动账户请求对象
 * @Author wkm
 * @Date 2024/11/16
 **/
@Data
public class UserActivityAccountRequestDTO {
    private String userId;
    private Long activityId;
}
