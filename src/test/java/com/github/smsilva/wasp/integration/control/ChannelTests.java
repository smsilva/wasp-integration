package com.github.smsilva.wasp.integration.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableIntegration
@ContextConfiguration(classes = {ChannelTestsConfig.class})
class ChannelTests {

	@Autowired
	DirectChannel input;

	@Autowired
	QueueChannel output;

	@Test
	public void should_OutputChannelReceiveAMessage_when_AMessageIsSentToInputChannel() {
		Message<String> message = new GenericMessage<>("Hello, world!");

		input.send(message);

		Message<?> receivedMessage = output.receive(0);

		assertNotNull(receivedMessage);
        assertEquals("Hello, world!", receivedMessage.getPayload());

		assertNull(output.receive(0));
	}

}
