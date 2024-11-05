package cn.good.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @Description 任务表 发送MQ
 * @Author wkm
 * @Date 2024/11/1
 **/
@Data
public class Task {
    private String id;
    private String userId;
    /* 消息主题 */
    private String topic;
    private String messageId;
    /* 消息主体 */
    private String message;
    private String state;
    private Date createTime;
    private Date updateTime;
}
