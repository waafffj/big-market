package cn.good.domain.credit.model.entity;

import cn.good.domain.credit.model.valobj.TradeNameVO;
import cn.good.domain.credit.model.valobj.TradeTypeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @Description 积分订单实体
 * @Author wkm
 * @Date 2024/11/30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditOrderEntity {
    private String userId;
    private String orderId;
    /** 交易名称 */
    private TradeNameVO tradeName;
    /** 交易类型；交易类型；forward-正向、reverse-逆向 */
    private TradeTypeVO tradeType;
    /** 交易金额 */
    private BigDecimal tradeAmount;
    /** 业务仿重ID - 外部透传。返利、行为等唯一标识 */
    private String outBusinessNo;

}
