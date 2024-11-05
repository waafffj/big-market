package cn.good.domain.task.model.entity;

import lombok.Data;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/4
 **/
@Data
public class TaskEntity {
    private String userId;
    private String topic;
    private String messageId;
    private String message;
}
