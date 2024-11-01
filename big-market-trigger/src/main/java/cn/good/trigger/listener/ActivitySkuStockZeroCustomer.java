package cn.good.trigger.listener;

import cn.good.domain.activity.service.IRaffleActivitySkuStockService;
import cn.good.types.event.BaseEvent;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Description 活动sku耗尽
 * @Author wkm
 * @Date 2024/10/30
 **/
@Slf4j
@Component
public class ActivitySkuStockZeroCustomer {
    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;
    @Resource
    private IRaffleActivitySkuStockService skuStock;
    @RabbitListener(queuesToDeclare = @Queue(value = "activity_sku_stock_zero"))
    public void listener(String message){
        try {
            log.info("监听活动sku库存消耗为0消息 topic: {} message: {}", topic, message);
            BaseEvent.EventMessage<Long> eventMessage = JSON.parseObject(message,new TypeReference<BaseEvent.EventMessage<Long>>(){
            }.getType());
            Long sku = eventMessage.getData();
            skuStock.clearActivitySkuStock(sku);
            skuStock.clearQueueValue();
        }catch (Exception e){
            log.error("监听活动sku库存消耗为0消息，消费失败 topic: {} message: {}", topic, message);
            throw e;
        }
    }
}
