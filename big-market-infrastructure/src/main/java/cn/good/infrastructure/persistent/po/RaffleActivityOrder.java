package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 *
 * @Description 抽奖活动单
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
public class RaffleActivityOrder {
    private Long id;
    private String userId;
    private Long sku;
    private Long activityId;
    private String activityName;
    private Long strategyId;
    private String orderId;
    private Date orderTime;
    private Integer totalCount;
    private Integer dayCount;
    private Integer monthCount;
    /* 支付金额 */
    private BigDecimal payAmount;
    /*  订单状态(not_used、used、expire)*/
    private String state;
     /*     业务防重ID  */
    private String outBusinessNo;
    private Date createTime;
    private Date updateTime;
}
