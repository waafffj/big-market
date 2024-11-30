package cn.good.domain.credit.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @Description 积分账户实体
 * @Author wkm
 * @Date 2024/11/30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccountEntity {
    private String userId;
    /* 可用积分 每次扣减的值 */
    private BigDecimal adjustAmount;
}
