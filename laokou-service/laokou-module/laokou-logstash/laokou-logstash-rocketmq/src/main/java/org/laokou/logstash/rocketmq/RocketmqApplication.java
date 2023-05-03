package org.laokou.logstash.rocketmq;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author laokou
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEncryptableProperties
public class RocketmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqApplication.class, args);
    }

}