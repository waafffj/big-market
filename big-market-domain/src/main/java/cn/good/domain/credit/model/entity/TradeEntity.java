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
 * @Description
 * @Author wkm
 * @Date 2024/11/30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeEntity {
    private String userId;
    /* 交易名称 */
    private TradeNameVO tradeName;
    private TradeTypeVO tradeType;
    private BigDecimal amount;
    private String outBusinessNo;
}
