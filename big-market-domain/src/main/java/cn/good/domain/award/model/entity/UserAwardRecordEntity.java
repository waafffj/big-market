package cn.good.domain.award.model.entity;

import cn.good.domain.award.model.valobj.AwardStateVO;
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
 * @Date 2024/11/4
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAwardRecordEntity {
    private String userId;
    private Long activityId;
    private Long strategyId;
    private String orderId;
    private Integer awardId;
    private String awardTitle;
    private Date awardTime;
    private AwardStateVO awardState;
}
