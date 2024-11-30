package cn.good.domain.award.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description 分发奖品实体
 * @Author wkm
 * @Date 2024/11/29
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DistributeAwardEntity {
    private String userId;
    private String orderId;
    private Integer awardId;
    /* 奖品配置信息 */
    private String awardConfig;
}
