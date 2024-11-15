package cn.good.domain.rebate.model.entity;

import cn.good.domain.award.model.valobj.TaskStateVO;
import cn.good.domain.rebate.event.SendRebateMessageEvent;
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
 * @Date 2024/11/15
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    private String userId;
    private String topic;
    private String messageId;
    private BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> message;
    private TaskStateVO state;
}
