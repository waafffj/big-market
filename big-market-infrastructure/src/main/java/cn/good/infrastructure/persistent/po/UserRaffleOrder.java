package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description 用户抽奖订单表
 * @Author wkm
 * @Date 2024/11/1
 **/
@Data
public class UserRaffleOrder {
    private String id;
    private String userId;
    private Long activityId;
    private String activityName;
    private Long strategyId;
    private String orderId;
    private Date orderTime;
    private String orderState;
    private Date createTime;
    private Date updateTime;
}
