package com.github.smsilva.wasp.integration.control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelTestsConfig {

    @Bean
    public MessageChannel input() {
        return new DirectChannel();
    }

    @Bean
    @BridgeFrom("input")
    public MessageChannel output() {
        return new QueueChannel();
    }

}
