package cn.good.domain.rebate.service;

import cn.good.domain.award.model.valobj.TaskStateVO;
import cn.good.domain.rebate.event.SendRebateMessageEvent;
import cn.good.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.good.domain.rebate.model.entity.BehaviorEntity;
import cn.good.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.good.domain.rebate.model.entity.TaskEntity;
import cn.good.domain.rebate.model.valobj.DailyBehaviorRebateVO;
import cn.good.domain.rebate.repository.IBehaviorRebateRepository;
import cn.good.types.common.Constants;
import cn.good.types.event.BaseEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/15
 **/
@Service
public class BehaviorRebateService implements IBehaviorRebateService{

    @Resource
    private IBehaviorRebateRepository behaviorRebateRepository;
    @Resource
    private SendRebateMessageEvent sendRebateMessageEvent;
    @Override
    public List<String> createOrder(BehaviorEntity behaviorEntity) {
        /* 查询返利配置 */
        List<DailyBehaviorRebateVO> dailyBehaviorRebateVOS = behaviorRebateRepository.queryDailyBehaviorRebateConfig(behaviorEntity.getBehaviorTypeVO());
        if(null == dailyBehaviorRebateVOS || dailyBehaviorRebateVOS.isEmpty()) return new ArrayList<>();

        /* 构建聚合对象 */
        List<String> orderIds = new ArrayList<>();
        List<BehaviorRebateAggregate> behaviorRebateAggregates = new ArrayList<>();
        for(DailyBehaviorRebateVO dailyBehaviorRebateVO : dailyBehaviorRebateVOS){
            String bizId = behaviorEntity.getUserId() + Constants.UNDERLINE + dailyBehaviorRebateVO.getRebateType() + Constants.UNDERLINE + behaviorEntity.getOutBusinessNo();
            BehaviorRebateOrderEntity behaviorRebateOrderEntity = BehaviorRebateOrderEntity.builder()
                    .userId(behaviorEntity.getUserId())
                    .orderId(RandomStringUtils.randomNumeric(12))
                    .behaviorType(dailyBehaviorRebateVO.getBehaviorType())
                    .rebateDesc(dailyBehaviorRebateVO.getRebateDesc())
                    .rebateType(dailyBehaviorRebateVO.getRebateType())
                    .rebateConfig(dailyBehaviorRebateVO.getRebateConfig())
                    .bizId(bizId)
                    .build();
            orderIds.add(behaviorRebateOrderEntity.getOrderId());


            /* MQ消息 */
            SendRebateMessageEvent.RebateMessage rebateMessage = SendRebateMessageEvent.RebateMessage.builder()
                    .userId(behaviorEntity.getUserId())
                    .rebateType(dailyBehaviorRebateVO.getRebateType())
                    .rebateConfig(dailyBehaviorRebateVO.getRebateConfig())
                    .bizId(bizId)
                    .build();

            /* 构建事件消息 */
            BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> rebateMessageEventMessage = sendRebateMessageEvent.buildEventMessage(rebateMessage);

            /* 组装任务对象 */
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setUserId(behaviorEntity.getUserId());
            taskEntity.setTopic(sendRebateMessageEvent.topic());
            taskEntity.setMessageId(rebateMessageEventMessage.getId());
            taskEntity.setMessage(rebateMessageEventMessage);
            taskEntity.setState(TaskStateVO.create);

            BehaviorRebateAggregate behaviorRebateAggregate = BehaviorRebateAggregate.builder()
                    .userId(behaviorEntity.getUserId())
                    .behaviorRebateOrderEntity(behaviorRebateOrderEntity)
                    .taskEntity(taskEntity)
                    .build();
            behaviorRebateAggregates.add(behaviorRebateAggregate);
        }
        /* 存储聚合对象  */
        behaviorRebateRepository.saveUserRebateRecord(behaviorEntity.getUserId(),behaviorRebateAggregates);
        return orderIds;
    }

    @Override
    public List<BehaviorRebateOrderEntity> queryOrderByOutBusinessNo(String userId, String outBusinessNo) {
        return behaviorRebateRepository.queryOrderByOutBusinessNo(userId,outBusinessNo);
    }
}
