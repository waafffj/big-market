package cn.good.domain.activity.service.partake;

import cn.good.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import cn.good.domain.activity.model.entity.ActivityEntity;
import cn.good.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.good.domain.activity.model.entity.UserRaffleOrderEntity;
import cn.good.domain.activity.model.valobj.ActivityStateVO;
import cn.good.domain.activity.repository.IActivityRepository;
import cn.good.domain.activity.service.IRaffleActivityPartakeService;
import cn.good.types.enums.ResponseCode;
import cn.good.types.exception.AppException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * TODO
 *
 * @Description 抽奖活动参与抽象类   定义流程
 * @Author wkm
 * @Date 2024/11/1
 **/
@Slf4j
public abstract class AbstractRaffleActivityPartake implements IRaffleActivityPartakeService {
    protected final IActivityRepository activityRepository;
    protected AbstractRaffleActivityPartake(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity) {
        String userId = partakeRaffleActivityEntity.getUserId();
        Long activityId = partakeRaffleActivityEntity.getActivityId();
        Date currentDate = new Date();
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activityId);
        if(!ActivityStateVO.open.equals(activityEntity.getState())){
            throw new AppException(ResponseCode.ACTIVITY_STATE_ERROR.getCode(),ResponseCode.ACTIVITY_STATE_ERROR.getInfo());
        }
        if(activityEntity.getBeginDateTime().after(currentDate) || activityEntity.getEndDateTime().before(currentDate)){
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(),ResponseCode.ACTIVITY_DATE_ERROR.getInfo());
        }
        UserRaffleOrderEntity userRaffleOrderEntity = activityRepository.queryNoUsedRaffleOrder(partakeRaffleActivityEntity);
        if(null != userRaffleOrderEntity){
            log.info("创建参与活动订单[已存在] userId:{} activityId:{} userRaffleOrderEntity:{}",userId,activityId, JSON.toJSONString(userRaffleOrderEntity));
            return userRaffleOrderEntity;
        }

        CreatePartakeOrderAggregate createPartakeOrderAggregate = this.doFilterAccount(userId,activityId,currentDate);
        UserRaffleOrderEntity userRaffleOrder = this.buildUserRaffleOrder(userId,activityId,currentDate);

        createPartakeOrderAggregate.setUserRaffleOrderEntity(userRaffleOrder);
        activityRepository.saveCreatePartakeOrderAggregate(createPartakeOrderAggregate);
        return userRaffleOrder;
    }
    protected abstract CreatePartakeOrderAggregate doFilterAccount(String userId,Long activityId,Date currentDate);
    protected abstract UserRaffleOrderEntity buildUserRaffleOrder(String userId,Long activityId,Date currentDate);
}
