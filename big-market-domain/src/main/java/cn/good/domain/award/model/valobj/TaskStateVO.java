package cn.good.domain.award.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/4
 **/
@Getter
@AllArgsConstructor
public enum TaskStateVO {
    create("create","创建"),
    complete("complete","发送完成"),
    fail("fail","发送失败"),
    ;
    private final String code;
    private final String desc;
}
