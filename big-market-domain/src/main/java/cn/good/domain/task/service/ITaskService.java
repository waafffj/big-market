package cn.good.domain.task.service;

import cn.good.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/4
 **/
public interface ITaskService {
    /**
     * 查询发送MQ失败和超时1分钟未发送的MQ
     */
    List<TaskEntity> queryNoSendMessageTaskList();
    void sendMessage(TaskEntity taskEntity);
    void updateTaskSendmessageCompleted(String userId,String messageId);
    void updateTaskSendmessageFail(String userId,String messageId);
}
