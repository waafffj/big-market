package cn.good.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/16
 **/
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleWeightVO {
    /* 原始规则值配置 */
    private String ruleValue;
    private Integer weight;
    private List<Integer> awardIds;
    private List<Award> awardList;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Award{
        private Integer awardId;
        private String awardTitle;
    }
}
