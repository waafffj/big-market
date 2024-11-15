package cn.good.domain.rebate.model.entity;

import cn.good.domain.rebate.model.valobj.BehaviorTypeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/15
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorEntity {
    private String userId;
    /* 行为类型 : sign 签到 openai_pay 支付*/
    private BehaviorTypeVO behaviorTypeVO;
    /* 业务ID; 签到则是日期字符串，支付则是外部的业务ID*/
    private String outBusinessNo;
}
