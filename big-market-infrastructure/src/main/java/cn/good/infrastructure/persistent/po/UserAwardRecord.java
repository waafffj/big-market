package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description 用户中奖记录表
 * @Author wkm
 * @Date 2024/11/1
 **/
@Data
public class UserAwardRecord {
    private String id;
    private String userId;
    private Long activityId;
    private Long strategyId;
    /** 抽奖订单ID【作为幂等使用】 */
    private String orderId;
    private Integer awardId;
    private String awardTitle;
    private Date awardTime;
    /* 奖品状态；create-创建、completed-发奖完成 */
    private String awardState;
    private Date createTime;
    private Date updateTime;
}
