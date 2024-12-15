package cn.good.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description 抽奖奖品列表,请求对象
 * @Author wkm
 * @Date 2024/10/20
 **/
@Data
public class RaffleAwardListRequestDTO implements Serializable {
    private String userId;
 /* 抽奖活动ID*/
    private Long activityId;
}
