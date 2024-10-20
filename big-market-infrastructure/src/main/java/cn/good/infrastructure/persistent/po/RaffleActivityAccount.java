package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description 抽奖活动账户表 持久化对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
public class RaffleActivityAccount {
    private Long id;
    private String userId;
    private Long activityId;
    private Integer totalCount;
    private Integer totalCountSurplus;
    private Integer dayCount;
    private Integer dayCountSurplus;
    private Integer monthCount;
    private Integer monthCountSurplus;
    private Date createTime;
    private Date updateTime;
}
