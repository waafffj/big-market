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
 * @Description
 * @Author wkm
 * @Date 2024/11/1
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleActivityAccountMonth {
    private final SimpleDateFormat dateFormatMonth = new SimpleDateFormat("yyyy-MM");
    private String id;
    private String userId;
    private Long activityId;
    /*  月(yyyy-mm)   */
    private String month;
    private Integer monthCount;
    private Integer monthCountSurplus;
    private Date createTime;
    private Date updateTime;

    public String currentMonth(){
        return dateFormatMonth.format(new Date());
    }
}
