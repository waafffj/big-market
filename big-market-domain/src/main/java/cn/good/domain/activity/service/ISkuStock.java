package cn.good.domain.activity.service;

import cn.good.domain.activity.model.valobj.ActivitySkuStockKeyVO;

/**
 * TODO
 *
 * @Description  活动sku库存处理接口
 * @Author wkm
 * @Date 2024/10/30
 **/
public interface ISkuStock {

    /**
     * 获取活动sku库存消耗队列
     * @return  奖品库存key信息
     * @throws InterruptedException
     */
    ActivitySkuStockKeyVO takeQueueValue() throws InterruptedException;
    void clearQueueValue();

    /**
     * 延迟队列 + 任务趋势更新活动sku库存
     * @param sku
     */
    void updateActivitySkuStock(Long sku);

    /**
     * 缓存库存以消耗完毕，清空数据库库存
     * @param sku
     */
    void clearActivitySkuStock(Long sku);
}
