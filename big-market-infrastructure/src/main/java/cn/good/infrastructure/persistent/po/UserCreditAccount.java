package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 *
 * @Description 用户积分账户
 * @Author wkm
 * @Date 2024/11/29
 **/
@Data
public class UserCreditAccount {
    private Long id;
    private String userId;
    /** 总积分，显示总账户值，记得一个人获得的总积分 */
    private BigDecimal totalAmount;
    private BigDecimal availableAmount;
    private String accountStatus;
    private Date createTime;
    private Date updateTime;
}
