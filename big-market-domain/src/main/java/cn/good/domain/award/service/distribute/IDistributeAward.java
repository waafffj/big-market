package cn.good.domain.award.service.distribute;

import cn.good.domain.award.model.entity.DistributeAwardEntity;

/**
 * TODO
 *
 * @Description 分发奖品接口
 * @Author wkm
 * @Date 2024/11/29
 **/
public interface IDistributeAward {
    void giveOutPrizes(DistributeAwardEntity distributeAwardEntity);
}
