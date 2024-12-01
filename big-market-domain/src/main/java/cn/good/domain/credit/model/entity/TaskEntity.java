package cn.good.domain.credit.model.entity;

import cn.good.domain.credit.event.CreditAdjustSuccessMessageEvent;
import cn.good.domain.rebate.model.valobj.TaskStateVO;
import cn.good.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/12/1
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    private String userId;
    private String topic;
    private String messageId;
    private BaseEvent.EventMessage<CreditAdjustSuccessMessageEvent.CreditAdjustSuccessMessage> message;
    private TaskStateVO state;
}
