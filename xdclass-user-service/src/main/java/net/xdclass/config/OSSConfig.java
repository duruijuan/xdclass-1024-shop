package net.xdclass.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.config
 * @className: OSSConfig
 * @author: duruijuan
 * @description:
 * @since: 2025-06-07 16:58
 * @version: 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketname;

}
