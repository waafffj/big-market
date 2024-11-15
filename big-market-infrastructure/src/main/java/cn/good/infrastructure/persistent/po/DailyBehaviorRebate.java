package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description  日常行为返利活动配置
 * @Author wkm
 * @Date 2024/11/15
 **/
@Data
public class DailyBehaviorRebate {
    private Long id;
    /* 行为类型 (sign 签到 openai_pay 支付)*/
    private String behaviorType;
    /* 返利描述 */
    private String rebateDesc;
    /* 返利类型 (sku 活动库存充值商品、 integral 用户活动积分) */
    private String rebateType;
    private String rebateConfig;
    /* 状态(open 开启 close 关闭)*/
    private String state;
    private Date createTime;
    private Date updateTime;
}
