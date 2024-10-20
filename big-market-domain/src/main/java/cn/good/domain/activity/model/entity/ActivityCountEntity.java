package cn.good.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description 活动次数实体对象
 * @Author wkm
 * @Date 2024/10/28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCountEntity {
    private Long activityCountId;
    private Integer totalCount;
    private Integer dayCount;
    private Integer monthCount;
}
