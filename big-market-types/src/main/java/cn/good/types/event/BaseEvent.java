package cn.good.types.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * TODO
 *
 * @Description 基础时间
 * @Author wkm
 * @Date 2024/10/30
 **/
@Data
public abstract class BaseEvent<T> {

    public abstract EventMessage<T> buildEventMessage(T data);
    public abstract String topic();


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventMessage<T>{
        private String id;
        private Date timestamp;
        private T data;
    }
}
