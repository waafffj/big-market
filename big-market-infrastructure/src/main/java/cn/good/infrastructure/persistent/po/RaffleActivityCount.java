package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description 抽奖活动次数配置表
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
public class RaffleActivityCount {
    private Long id;
    /*   活动次数编号   */
    private Long activityCountId;
    private Integer totalCount;
    private Integer dayCount;
    private Integer monthCount;
    private Date createTime;
    private Date updateTime;
}
