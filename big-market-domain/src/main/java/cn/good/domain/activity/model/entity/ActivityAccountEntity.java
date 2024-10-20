package cn.good.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description 活动账户实体对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityAccountEntity {
    private String userId;
    private Long activityId;
    private Integer totalCount;
    private Integer totalCountSurplus;
    private Integer dayCount;
    private Integer dayCountSurplus;
    private Integer monthCount;
    private Integer monthCountSurplus;
}
