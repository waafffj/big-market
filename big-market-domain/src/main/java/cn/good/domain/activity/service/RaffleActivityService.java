package cn.good.domain.activity.service;

import cn.good.domain.activity.repository.IActivityRepository;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @Description 抽奖活动服务
 * @Author wkm
 * @Date 2024/10/28
 **/
@Service
public class RaffleActivityService extends AbstractRaffleActivity{
    public RaffleActivityService(IActivityRepository activityRepository) {
        super(activityRepository);
    }
}
