package cn.good.domain.activity.model.entity;

import cn.good.domain.activity.model.valobj.UserRaffleOrderStateVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/12/11
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTenRaffleOrderEntity {
    private String userId;
    private Long activityId;
    private String activityName;
    private Long strategyId;
    private List<String> orderIds;
    private Date orderTime;
    private UserRaffleOrderStateVO orderState;
    private Date endDateTime;
}
