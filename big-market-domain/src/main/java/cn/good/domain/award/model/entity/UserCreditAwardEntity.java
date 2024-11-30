package cn.good.domain.award.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @Description 用户积分奖品实体对象
 * @Author wkm
 * @Date 2024/11/29
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreditAwardEntity {
    private String userId;
    /** 积分值 */
    private BigDecimal creditAmount;
}
