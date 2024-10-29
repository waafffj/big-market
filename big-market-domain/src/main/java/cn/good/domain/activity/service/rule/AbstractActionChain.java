package cn.good.domain.activity.service.rule;

/**
 * TODO
 *
 * @Description  下单规则责任链抽象类
 * @Author wkm
 * @Date 2024/10/29
 **/
public abstract class AbstractActionChain implements IActionChain{

    private IActionChain next;

    @Override
    public IActionChain next() {
        return null;
    }

    @Override
    public IActionChain appendNext(IActionChain next) {
        this.next = next;
        return next;
    }
}
