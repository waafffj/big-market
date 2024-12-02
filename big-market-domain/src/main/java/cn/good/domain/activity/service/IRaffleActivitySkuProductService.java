package cn.good.domain.activity.service;

import cn.good.domain.activity.model.entity.SkuProductEntity;

import java.util.List;

/**
 * TODO
 *
 * @Description sku商品服务接口
 * @Author wkm
 * @Date 2024/12/2
 **/
public interface IRaffleActivitySkuProductService {
    /**
     * 查询当前活动id下，创建的sku商品。【sku可以兑换活动抽奖次数】
     * @param activityId
     * @return sku商品集合
     */
    List<SkuProductEntity> querySkuProductEntityListByActivityId(Long activityId);
}
