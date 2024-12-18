package cn.good.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/12/15
 **/
@Data
@ConfigurationProperties(prefix = "zookeeper.sdk.config",ignoreInvalidFields = true)
public class ZookeeperClientConfigProperties {
    private String connectString;
    private int baseSleepTimeMs;
    private int maxRetries;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
}
