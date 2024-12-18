package cn.good;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configurable
@EnableScheduling
@EnableDubbo
/** 启用 Spring AOP 的自动代理功能，并指定使用 CGLIB 代理来生成代理类  */
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

}
