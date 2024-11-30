package cn.good.domain.award.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @Description 账户状态枚举
 * @Author wkm
 * @Date 2024/11/29
 **/
@Getter
@AllArgsConstructor
public enum AccountStatusVO {
    open("open","开启"),
    close("close","冻结"),
    ;
    private final String code;
    private final String desc;
}
