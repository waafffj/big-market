package cn.good.domain.award.service;

import cn.good.domain.award.event.SendAwardMessageEvent;
import cn.good.domain.award.model.aggregate.UserAwardRecordAggregate;
import cn.good.domain.award.model.entity.DistributeAwardEntity;
import cn.good.domain.award.model.entity.TaskEntity;
import cn.good.domain.award.model.entity.UserAwardRecordEntity;
import cn.good.domain.award.model.valobj.TaskStateVO;
import cn.good.domain.award.repository.IAwardRepository;
import cn.good.domain.award.service.distribute.IDistributeAward;
import cn.good.types.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * TODO
 *
 * @Description 奖品服务
 * @Author wkm
 * @Date 2024/11/4
 **/
@Slf4j
@Service
public class AwardService implements IAwardService{

    private final IAwardRepository awardRepository;

    private final SendAwardMessageEvent sendAwardMessageEvent;
    private final Map<String, IDistributeAward> distributeAwardMap;

    public AwardService(IAwardRepository awardRepository, SendAwardMessageEvent sendAwardMessageEvent, Map<String, IDistributeAward> distributeAwardMap) {
        this.awardRepository = awardRepository;
        this.sendAwardMessageEvent = sendAwardMessageEvent;
        this.distributeAwardMap = distributeAwardMap;
    }

    @Override
    public void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity) {
        SendAwardMessageEvent.SendAwardMessage sendAwardMessage = new SendAwardMessageEvent.SendAwardMessage();
        sendAwardMessage.setUserId(userAwardRecordEntity.getUserId());
        sendAwardMessage.setOrderId(userAwardRecordEntity.getOrderId());
        sendAwardMessage.setAwardId(userAwardRecordEntity.getAwardId());
        sendAwardMessage.setAwardTitle(userAwardRecordEntity.getAwardTitle());
        sendAwardMessage.setAwardConfig(userAwardRecordEntity.getAwardConfig());


        BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> sendAwardMessageEventMessage = sendAwardMessageEvent.buildEventMessage(sendAwardMessage);
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUserId(userAwardRecordEntity.getUserId());
        taskEntity.setTopic(sendAwardMessageEvent.topic());
        taskEntity.setMessageId(sendAwardMessageEventMessage.getId());
        taskEntity.setMessage(sendAwardMessageEventMessage);
        taskEntity.setState(TaskStateVO.create);

        UserAwardRecordAggregate userAwardRecordAggregate = UserAwardRecordAggregate.builder()
                .userAwardRecordEntity(userAwardRecordEntity)
                .taskEntity(taskEntity)
                .build();
        awardRepository.saveUserAwardRecord(userAwardRecordAggregate);
    }

    @Override
    public void distributeAward(DistributeAwardEntity distributeAwardEntity) {
        String awardKey = awardRepository.queryAwardKey(distributeAwardEntity.getAwardId());
        if(null == awardKey){
            log.error("分发奖品，奖品ID不存在。awardKey:{}", awardKey);
            return;
        }
        IDistributeAward distributeAward = distributeAwardMap.get(awardKey);
        if(null == distributeAward){
            log.error("分发奖品，对应的服务不存在。awardKey:{}", awardKey);
            throw new RuntimeException("分发奖品，奖品" + awardKey + "对应的服务不存在");
        }
        distributeAward.giveOutPrizes(distributeAwardEntity);
    }
}
