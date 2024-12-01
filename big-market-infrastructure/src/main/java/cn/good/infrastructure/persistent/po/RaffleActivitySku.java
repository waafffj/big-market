package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 *
 * @Description 抽奖活动sku持久化对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
public class RaffleActivitySku {
    /*  商品sku  */
    private Long sku;
    private Long activityId;
    /*  活动个人参与次数Id*/
    private Long activityCountId;
    /*  库存总量    */
    private Integer stockCount;
    /*  剩余库存    */
    private Integer stockCountSurplus;
    /* 商品金额 */
    private BigDecimal productAmount;
    private Date createTime;
    private Date updateTime;
}
