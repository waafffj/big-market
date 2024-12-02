package cn.good.trigger.job;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.good.domain.task.model.entity.TaskEntity;
import cn.good.domain.task.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO
 *
 * @Description 发送MQ消息队列
 * @Author wkm
 * @Date 2024/11/4
 **/
@Slf4j
@Component()
public class SendMessageTaskJob {
    @Resource
    private ITaskService taskService;
    @Resource
    private ThreadPoolExecutor executor;
    @Resource
    private IDBRouterStrategy dbRouter;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec_db01() {
        try{
            /* 设置库表*/
            dbRouter.setDBKey(1);
            dbRouter.setTBKey(0);
            /* 查询未发送任务*/
            List<TaskEntity> taskEntities = taskService.queryNoSendMessageTaskList();
            if(taskEntities.isEmpty()) return;
            for(TaskEntity taskEntity : taskEntities){
                try {
                    taskService.sendMessage(taskEntity);
                    taskService.updateTaskSendMessageCompleted(taskEntity.getUserId(), taskEntity.getMessageId());
                }catch (Exception e){
                    log.error("定时任务，发送MQ消息失败 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic());
                    taskService.updateTaskSendMessageFail(taskEntity.getUserId(), taskEntity.getMessageId());
                }
            }
        }catch (Exception e){
            log.error("定时任务，扫描MQ任务表发送消息失败。", e);
        }finally {
            dbRouter.clear();
        }
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec_db02() {
        try{
            /* 设置库表*/
            dbRouter.setDBKey(2);
            dbRouter.setTBKey(0);
            /* 查询未发送任务*/
            List<TaskEntity> taskEntities = taskService.queryNoSendMessageTaskList();
            if(taskEntities.isEmpty()) return;
            for(TaskEntity taskEntity : taskEntities){
                try {
                    taskService.sendMessage(taskEntity);
                    taskService.updateTaskSendMessageCompleted(taskEntity.getUserId(), taskEntity.getMessageId());
                }catch (Exception e){
                    log.error("定时任务，发送MQ消息失败 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic());
                    taskService.updateTaskSendMessageFail(taskEntity.getUserId(), taskEntity.getMessageId());
                }
            }
        }catch (Exception e){
            log.error("定时任务，扫描MQ任务表发送消息失败。", e);
        }finally {
            dbRouter.clear();
        }
    }


}
