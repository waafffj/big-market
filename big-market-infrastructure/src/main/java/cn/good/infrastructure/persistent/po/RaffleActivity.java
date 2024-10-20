package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description 抽奖活动表 持久化对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
public class RaffleActivity {
    private Long id;
    private Long activityId;
    private String activityName;
    private String activityDesc;
    private Date beginDateTime;
    private Date endDateTime;
    private Long strategyId;
    private String state;
    private Date createTime;
    private Date updateTime;
}
