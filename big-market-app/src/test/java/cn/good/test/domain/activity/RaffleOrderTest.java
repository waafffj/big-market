package cn.good.test.domain.activity;

import cn.good.domain.activity.model.entity.ActivityOrderEntity;
import cn.good.domain.activity.model.entity.ActivityShopCartEntity;
import cn.good.domain.activity.service.IRaffleOrder;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/10/28
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleOrderTest {

 @Resource
 private IRaffleOrder raffleOrder;

 @Test
 public void test_createRaffleActivityOrder() {
  ActivityShopCartEntity activityShopCartEntity = new ActivityShopCartEntity();
  activityShopCartEntity.setUserId("xiaofuge");
  activityShopCartEntity.setSku(9011L);
  ActivityOrderEntity raffleActivityOrder = raffleOrder.createRaffleActivityOrder(activityShopCartEntity);
  log.info("测试结果：{}", JSON.toJSONString(raffleActivityOrder));
 }

}
