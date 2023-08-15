package com.github.smsilva.wasp.integration;

import com.github.smsilva.wasp.integration.control.SimpleStringLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.splitter.FileSplitter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
public class IntegrationConfig {

    public static class Channels {
        public static final String FILE_INPUT_CHANNEL = "fileInputChannel";
        public static final String PROCESS_FILE_CHANNEL = "processFileChannel";
    }

    @Bean
    public MessageChannel stringMessages() {
        return new DirectChannel();
    }

    @Value("${integration.inbound.path}")
    private String INBOUND_PATH;

    @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel processFileChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = Channels.FILE_INPUT_CHANNEL, poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(INBOUND_PATH));
        source.setFilter(new SimplePatternFileListFilter("*.txt"));
        source.setUseWatchService(true);
        source.setWatchEvents(FileReadingMessageSource.WatchEventType.CREATE);
        return source;
    }

    @Bean
    @Splitter(inputChannel = Channels.FILE_INPUT_CHANNEL)
    public MessageHandler fileSplitter() {
        FileSplitter splitter = new FileSplitter(true, false);
        splitter.setApplySequence(true);
        splitter.setOutputChannelName(Channels.PROCESS_FILE_CHANNEL);
        return splitter;
    }

    @Bean
    @ServiceActivator(inputChannel = Channels.PROCESS_FILE_CHANNEL)
    public SimpleStringLogger stringLoggerDemo() {
        return new SimpleStringLogger();
    }

}
