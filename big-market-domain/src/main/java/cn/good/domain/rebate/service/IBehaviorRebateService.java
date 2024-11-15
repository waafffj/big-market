package cn.good.domain.rebate.service;

import cn.good.domain.rebate.model.entity.BehaviorEntity;

import java.util.List;

/**
 * TODO
 *
 * @Description 行为返利服务接口
 * @Author wkm
 * @Date 2024/11/15
 **/
public interface IBehaviorRebateService {
    List<String> createOrder(BehaviorEntity behaviorEntity);
}
