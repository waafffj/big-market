package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/30
 **/
@Data
public class UserCreditOrder {
    private Long id;
    private String userId;
    private String orderId;
    /* 交易名称*/
    private String tradeName;
    /* 交易类型*/
    private String tradeType;
    /* 交易金额*/
    private BigDecimal tradeAmount;
    /* 业务防重ID*/
    private String outBusinessNo;
    private Date createTime;
    private Date updateTime;
}
