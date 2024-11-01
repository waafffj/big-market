package cn.good.domain.activity.event;

import cn.good.types.event.BaseEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * TODO
 *
 * @Description 活动sku库存清空消息
 * @Author wkm
 * @Date 2024/10/30
 **/
@Component
public class ActivitySkuStockZeroMessageEvent extends BaseEvent<Long> {
    /* 发放sku long 类型的消息*/
    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;
    @Override
    public EventMessage<Long> buildEventMessage(Long sku) {
        return EventMessage.<Long>builder()
                .id(RandomStringUtils.randomNumeric(11))
                .timestamp(new Date())
                .data(sku)
                .build();
    }

    @Override
    public String topic() {
        return topic;
    }
}
