package cn.good.domain.activity.model.aggregate;

import cn.good.domain.activity.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description 参与活动订单聚合对象
 * @Author wkm
 * @Date 2024/11/1
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePartakeOrderAggregate {
    private String userId;
    private Long activityId;
    private ActivityAccountEntity activityAccountEntity;
    private boolean isExistAccountMonth = true;
    private ActivityAccountMonthEntity activityAccountMonthEntity;
    private boolean isExistAccountDay = true;
    private ActivityAccountDayEntity activityAccountDayEntity;
    private UserRaffleOrderEntity userRaffleOrderEntity;
    private UserTenRaffleOrderEntity userTenRaffleOrderEntity;
}
