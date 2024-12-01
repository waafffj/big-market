package cn.good.domain.activity.model.aggregate;

import cn.good.domain.activity.model.entity.ActivityOrderEntity;
import cn.good.domain.activity.model.valobj.OrderStateVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description 下单聚合对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuotaOrderAggregate {
    private String userId;
    private Long activityId;
    /*  增加总次数 */
    private Integer totalCount;
    /*  增加日次数 */
    private Integer dayCount;
    private Integer monthCount;
    private ActivityOrderEntity activityOrderEntity;
    public void setOrderState(OrderStateVO orderState){
        this.activityOrderEntity.setState(orderState);
    }
}
