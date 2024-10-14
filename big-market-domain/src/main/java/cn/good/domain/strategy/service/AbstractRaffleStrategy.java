package cn.good.domain.strategy.service;
import cn.good.domain.strategy.model.entity.RaffleAwardEntity;
import cn.good.domain.strategy.model.entity.RaffleFactorEntity;
import cn.good.domain.strategy.model.entity.RuleActionEntity;
import cn.good.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.good.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import cn.good.domain.strategy.repository.IStrategyRepository;
import cn.good.domain.strategy.service.armory.IStrategyDispatch;
import cn.good.domain.strategy.service.rule.chain.ILogicChain;
import cn.good.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.good.types.enums.ResponseCode;
import cn.good.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {
    protected IStrategyRepository repository;
    protected IStrategyDispatch strategyDispatch;
    private final DefaultChainFactory  defaultLogicFactory;
    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch,DefaultChainFactory defaultLogicFactory){
        this.repository = repository;
        this.strategyDispatch = strategyDispatch;
        this.defaultLogicFactory = defaultLogicFactory;
    }


    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {
//        1.参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        ILogicChain logicChain = defaultLogicFactory.openLogicChain(strategyId);
//              默认抽奖流程
        Integer awardId = logicChain.logic(userId, strategyId);
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);

        RuleActionEntity<RuleActionEntity.RaffleCenterEntity> ruleActionCenterEntity = this.doCheckRaffleCenterLogic(RaffleFactorEntity.builder()
                .userId(userId)
                .strategyId(strategyId)
                .awardId(awardId)
                .build(), strategyAwardRuleModelVO.raffleCenterRuleModelList());

        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionCenterEntity.getCode())) {
            log.info("【临时日志】中奖中规则拦截，通过抽奖后规则 rule_luck_award 走兜底奖励。");
            return RaffleAwardEntity.builder()
                    .awardDesc("中奖中规则拦截,通过抽奖后规则 rule_luck_award 走兜底奖励。")
                    .build();
        }
        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();
    }
    protected abstract RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffleCenterLogic(RaffleFactorEntity raffleFactorEntity,String... logics);
}
