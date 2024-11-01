package cn.good.domain.activity.service.quota.rule;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/10/29
 **/
public interface IActionChainArmory {
    IActionChain next();
    IActionChain appendNext(IActionChain next);
}
