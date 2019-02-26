package com.nova.sbnetty.config;

import com.nova.sbnetty.protocol.CustomProtocol;
import com.nova.sbnetty.protocol.PingPongProtocol;
import com.nova.sbnetty.utils.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeartbeatConfig {

    @Bean("clientHeartbeat")
    public CustomProtocol clientHeartBeat() {
        return new PingPongProtocol(IdGenerator.getNextId(),"ping");
    }
}
