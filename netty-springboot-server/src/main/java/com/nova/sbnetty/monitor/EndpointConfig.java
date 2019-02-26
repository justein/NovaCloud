package com.nova.sbnetty.monitor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @ClassName: EndpointConfig
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/2/24 下午9:17
 * @version : V1.0
 */
@Configuration
public class EndpointConfig {

    @Bean
    public ClientMapEndpoint buildEndpoint() {
        return new ClientMapEndpoint("channelMap");
    }
}
