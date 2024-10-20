package cn.good.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/10/28
 **/
@Getter
@AllArgsConstructor
public enum ActivityStateVO{
    create("create","创建"),
    open("open","开启"),
    close("close","关闭"),
    ;
    private final String code;
    private final String desc;
}
