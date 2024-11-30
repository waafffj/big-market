package cn.good.test.domain.credit;

import cn.good.domain.credit.model.entity.TradeEntity;
import cn.good.domain.credit.model.valobj.TradeNameVO;
import cn.good.domain.credit.model.valobj.TradeTypeVO;
import cn.good.domain.credit.service.ICreditAdjustService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/30
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditAdjustServiceTest {
    @Resource
    private ICreditAdjustService creditAdjustService;
    @Test
    public void test_createOrder_forward(){
        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setUserId("xiaofuge");
        tradeEntity.setTradeName(TradeNameVO.REBATE);
        tradeEntity.setTradeType(TradeTypeVO.FORWARD);
        tradeEntity.setAmount(new BigDecimal("10.19"));
        tradeEntity.setOutBusinessNo("100000009901");
        creditAdjustService.createOrder(tradeEntity);
    }
}
