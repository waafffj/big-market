package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description 抽奖活动账户表-日次数
 * @Author wkm
 * @Date 2024/11/1
 **/
@Data
public class RaffleActivityAccountDay {
    private String id;
    private String userId;
    private Long activityId;
    /*   日期(yyyy-mm-dd)     */
    private String day;
    private Integer dayCount;
    private Integer datCountSurplus;
    private Date createTime;
    private Date updateTime;
}
