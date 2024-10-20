package cn.good.domain.activity.model.entity;

import cn.good.domain.activity.model.valobj.ActivityStateVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEntity {
    private Long activityId;
    private String activityName;
    private String activityDesc;
    private Date beginDateTime;
    private Date endDateTime;
    private Long activityCountId;
    private Long strategyId;
    private ActivityStateVO state;
}
