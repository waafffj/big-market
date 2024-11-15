package cn.good.domain.rebate.model.aggregate;

import cn.good.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.good.domain.rebate.model.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/15
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorRebateAggregate {
    private String userId;
    /* 行为返利订单实体对象 */
    private BehaviorRebateOrderEntity behaviorRebateOrderEntity;
    private TaskEntity taskEntity;
}
