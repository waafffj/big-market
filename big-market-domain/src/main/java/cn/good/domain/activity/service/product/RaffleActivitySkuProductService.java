package cn.good.domain.activity.service.product;

import cn.good.domain.activity.model.entity.SkuProductEntity;
import cn.good.domain.activity.repository.IActivityRepository;
import cn.good.domain.activity.service.IRaffleActivitySkuProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @Description shu商品服务
 * @Author wkm
 * @Date 2024/12/2
 **/
@Service
public class RaffleActivitySkuProductService implements IRaffleActivitySkuProductService {

    @Resource
    private IActivityRepository repository;
    @Override
    public List<SkuProductEntity> querySkuProductEntityListByActivityId(Long activityId) {
        return repository.querySkuProductEntityListByActivityId(activityId);
    }
}
