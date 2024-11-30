package cn.good.infrastructure.persistent.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @Description 抽奖活动账户表-日次数
 * @Author wkm
 * @Date 2024/11/1
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleActivityAccountDay {
    private final static SimpleDateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
    private Long id;
    private String userId;
    private Long activityId;
    /*   日期(yyyy-mm-dd)     */
    private String day;
    private Integer dayCount;
    private Integer dayCountSurplus;
    private Date createTime;
    private Date updateTime;
    public static String currentDay(){
        return dateFormatDay.format(new Date());
    }
}
