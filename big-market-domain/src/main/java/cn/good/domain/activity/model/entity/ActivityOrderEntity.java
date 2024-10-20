package cn.good.domain.activity.model.entity;

import cn.good.domain.activity.model.valobj.OrderStateVO;
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
public class ActivityOrderEntity {
    private String userId;
    private Long activityId;
    private String activityName;
    private Long strategyId;
    private String orderId;
    private Date orderTime;
    private Integer totalCount;
    private Integer dayCount;
    private Integer monthCount;
    private OrderStateVO state;
}
