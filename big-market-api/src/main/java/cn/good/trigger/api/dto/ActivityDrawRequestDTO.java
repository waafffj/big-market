package cn.good.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/6
 **/
@Data
public class ActivityDrawRequestDTO implements Serializable {
    private String userId;
    private Long activityId;
}
