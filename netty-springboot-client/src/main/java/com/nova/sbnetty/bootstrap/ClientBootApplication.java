package com.nova.sbnetty.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/***
 * @ClassName: BootApplication
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/2/24 下午9:23
 * @version : V1.0
 */
@SpringBootApplication
@ComponentScan("com.nova.sbnetty")
public class ClientBootApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientBootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientBootApplication.class, args);
    }
}
