package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.RaffleActivitySku;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @Description 商品sku Dao
 * @Author wkm
 * @Date 2024/10/28
 **/
@Mapper
public interface IRaffleActivitySkuDao {
    RaffleActivitySku queryActivitySku(Long sku);
}
