package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/1
 **/
@Data
public class RaffleActivityAccountMonth {
    private String id;
    private String userId;
    private Long activityId;
    /*  月(yyyy-mm)   */
    private String month;
    private Integer monthCount;
    private Integer monthCountSurplus;
    private Date createTime;
    private Date updateTime;
}
