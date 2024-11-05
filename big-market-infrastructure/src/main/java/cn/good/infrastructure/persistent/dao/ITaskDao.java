package cn.good.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.good.infrastructure.persistent.po.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/1
 **/
@Mapper
public interface ITaskDao {
    void insert(Task task);
    @DBRouter
    void updateTaskSendMessageCompleted(Task task);
    @DBRouter
    void updateTaskSendMessageFail(Task task);
    List<Task> queryNoSendMessageTaskList();
}
