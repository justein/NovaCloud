package com.nova.sbnetty.bootstrap;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/***
 * @ClassName: com.nova.sbnetty.bootstrap.MonitorApplication
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/2/26 下午10:26
 * @version : V1.0
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableAdminServer
@ComponentScan("com.nova.sbnetty")
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class,args);
    }
}
