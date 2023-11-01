package com.chafan.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @Auther: 茶凡
 * @ClassName MongoConfig
 * @date 2023/10/30 0:02
 * @Description TODO
 */
@Configuration
@ConfigurationProperties(prefix = "connection")
public class MongoConfig {

    @Value("${connection.url}")
    public static String url;

}
