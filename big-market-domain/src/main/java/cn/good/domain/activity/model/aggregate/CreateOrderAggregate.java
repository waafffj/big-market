package cn.good.domain.activity.model.aggregate;

import cn.good.domain.activity.model.entity.ActivityAccountEntity;
import cn.good.domain.activity.model.entity.ActivityOrderEntity;
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
public class CreateOrderAggregate {
    /*  活动账户实体  */
    private ActivityAccountEntity activityAccountEntity;
    private ActivityOrderEntity activityOrderEntity;
}
