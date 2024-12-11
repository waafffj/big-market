package cn.good.test.domain.strategy;

import cn.good.domain.strategy.model.entity.RaffleAwardEntity;
import cn.good.domain.strategy.model.entity.RaffleFactorEntity;
import cn.good.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.good.domain.strategy.repository.IStrategyRepository;
import cn.good.domain.strategy.service.IRaffleStock;
import cn.good.domain.strategy.service.IRaffleStrategy;
import cn.good.domain.strategy.service.armory.IStrategyArmory;
import cn.good.domain.strategy.service.rule.chain.impl.RuleWeightLogicChain;
import cn.good.domain.strategy.service.rule.tree.impl.RuleLockLogicTreeNode;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @autho
 * @description 抽奖策略测试
 * @create
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyTest {

    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IRaffleStrategy raffleStrategy;
    @Resource
    private RuleWeightLogicChain ruleWeightLogicChain;
    @Resource
    private RuleLockLogicTreeNode ruleLockLogicTreeNode;

    @Resource
    private IRaffleStock raffleStock;
    @Resource
    private IStrategyRepository strategyRepository;

    @Test
    public void test_todayCount() throws InterruptedException {
        Integer i = strategyRepository.queryActivityAccountTotalUseCount("wkm", 100006L);
        log.info("测试结果: i = {}",i);
    }
}

