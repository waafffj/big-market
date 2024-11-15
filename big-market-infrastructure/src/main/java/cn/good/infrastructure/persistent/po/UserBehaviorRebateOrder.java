package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description 用户行为返利流水订单表
 * @Author wkm
 * @Date 2024/11/15
 **/
@Data
public class UserBehaviorRebateOrder {
    private Long id;
    private String userId;
    private String orderId;
    /* 行为类型 (sign 签到、openai_pay 支付 )*/
    private String behaviorType;
    private String rebateDesc;
    private String rebateType;
    private String rebateConfig;
    /* 业务Id 拼接的唯一值*/
    private String bizId;
    private Date createTime;
    private Date updateTime;
}
