package cn.good.trigger.api;

import cn.good.trigger.api.dto.RaffleAwardListRequestDTO;
import cn.good.trigger.api.dto.RaffleAwardListResponseDTO;
import cn.good.trigger.api.dto.RaffleRequestDTO;
import cn.good.trigger.api.dto.RaffleResponseDTO;
import cn.good.types.model.Response;

import java.util.List;

/**
 * TODO
 *
 * @Description 抽奖服务接口
 * @Author wkm
 * @Date 2024/10/20
 **/
public interface IRaffleService {

    /**
     * 策略装配接口
     * @param strategyId
     * @return 装配结果
     */
    Response<Boolean> strategyArmory(Long strategyId);

    /**
     * 查询抽奖奖品列表
     * @param requestDTO 抽奖奖品列表查询请求参数
     * @return 奖品列表数据
     */

    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);


    /**
     * 随机抽奖接口
     * @param requestDTO 请求参数
     * @return 抽奖结果
     */
    Response<RaffleResponseDTO> randomRaffle(RaffleRequestDTO requestDTO);
}
