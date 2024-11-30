package cn.good.domain.award.event;

import cn.good.types.event.BaseEvent;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


import java.util.Date;

/**
 * TODO
 *
 * @Description 用户奖品记录
 * @Author wkm
 * @Date 2024/11/4
 **/
@Component
public class SendAwardMessageEvent extends BaseEvent<SendAwardMessageEvent.SendAwardMessage> {
    @Value("${spring.rabbitmq.topic.send_award}")
    private String topic;


    @Override
    public EventMessage<SendAwardMessage> buildEventMessage(SendAwardMessage data) {
           return EventMessage.<SendAwardMessage>builder()
                .id(RandomStringUtils.randomNumeric(11))
                .timestamp(new Date())
                .data(data)
                .build();
    }

    @Override
    public String topic() {
        return topic;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SendAwardMessage{
        private String userId;
        private String orderId;
        private Integer awardId;
        private String awardTitle;
        private String awardConfig;
    }
}
