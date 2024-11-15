package cn.good.domain.rebate.repository;

import cn.good.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.good.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.good.domain.rebate.model.valobj.DailyBehaviorRebateVO;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/15
 **/
public interface IBehaviorRebateRepository {
    List<DailyBehaviorRebateVO> queryDailyBehaviorRebateConfig(BehaviorTypeVO behaviorTypeVO);
    void saveUserRebateRecord(String userId, List<BehaviorRebateAggregate> behaviorRebateAggregates);
}
