package cn.good.domain.rebate.model.entity;

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
public class BehaviorRebateOrderEntity {
    private String userId;
    private String orderId;
    private String behaviorType;
    private String rebateDesc;
    private String rebateType;
    /* 返利配置 */
    private String rebateConfig;
    private String outBusinessNo;
    /* 业务ID */
    private String bizId;
}
