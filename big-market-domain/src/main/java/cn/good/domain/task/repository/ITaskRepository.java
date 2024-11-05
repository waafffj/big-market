package cn.good.domain.task.repository;

import cn.good.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/4
 **/
public interface ITaskRepository {
    List<TaskEntity> queryNoSendMessageTaskList();
    void sendMessage(TaskEntity taskEntity);
    void updateTaskSendMessageCompleted(String userId,String messageId);
    void updateTaskSendMessageFail(String userId,String messageId);
}
