package cn.good.trigger.api;

import cn.good.trigger.api.dto.*;
import cn.good.types.model.Response;

import java.util.List;

/**
 * TODO
 *
 * @Description 抽奖服务接口
 * @Author wkm
 * @Date 2024/10/20
 **/
public interface IRaffleStrategyService {

    /**
     * 策略装配接口
     * @param strategyId
     * @return 装配结果
     */
    Response<Boolean> strategyArmory(Long strategyId);

    /**
     * 查询抽奖奖品列表
     * @param request 抽奖奖品列表查询请求参数
     * @return 奖品列表数据
     */

    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO request);


    /**
     * 随机抽奖接口
     * @param request 请求参数
     * @return 抽奖结果
     */
    Response<RaffleStrategyResponseDTO> randomRaffle(RaffleStrategyRequestDTO request);

    /**
     * 查询抽奖策略权重规则，给用户展示出抽奖N次后必中奖奖品范围
     *
     * @param request 请求对象
     * @return 权重奖品配置列表「这里会返回全部，前端可按需展示一条已达标的，或者一条要达标的」
     */
    Response<List<RaffleStrategyRuleWeightResponseDTO>> queryRaffleStrategyRuleWeight(RaffleStrategyRuleWeightRequestDTO request);
}
