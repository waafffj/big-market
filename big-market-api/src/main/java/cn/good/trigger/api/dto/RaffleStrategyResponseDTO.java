package cn.good.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/10/20
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleStrategyResponseDTO implements Serializable {
    private Integer awardId;
    /* 排序编号 【策略奖品的顺序编号】*/
    private Integer awardIndex;
}
