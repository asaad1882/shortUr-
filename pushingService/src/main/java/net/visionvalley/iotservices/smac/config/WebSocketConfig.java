package net.visionvalley.iotservices.smac.config;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.StompBrokerRelayRegistration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import net.visionvalley.iotservices.smac.queuesconfiguarions.Queue;
import net.visionvalley.iotservices.smac.services.UserPresenceService;

/**
 * Enable and configure Stomp over WebSocket.
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	private static final int MESSAGE_BUFFER_SIZE = 8192;
	private static final long SECOND_IN_MILLIS = 1000L;
	private static final long HOUR_IN_MILLIS = SECOND_IN_MILLIS * 60 * 60;
	private final int OUTBOUND_CHANNEL_CORE_POOL_SIZE = 8;
	
	@Value("${broker.enabled}")
	private boolean brokerEnabled;

	@Value("${broker.login}")
	private String brokerLogin;

	@Value("${broker.password}")
	private String brokerPassword;

	@Value("${broker.host}")
	private String brokerHost;

	@Value("${broker.port}")
	private int brokerPort;

	@Value("${broker.virtual-host}")
    private String brokerVirtualHost;
	
	 @Bean
	    public BrokerService brokerService() throws Exception {
		 	
		 return BrokerFactory.createBroker(String.format("broker:(vm://localhost,stomp://localhost:%d)" +
				 "?persistent=false&useJmx=false&useShutdownHook=true", 8092));
	}

	/**
	 * Register Stomp endpoints: the url to open the WebSocket connection.
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		// Register the "/ws" endpoint, enabling the SockJS protocol.
		// SockJS is used (both client and server side) to allow alternative
		// messaging options if WebSocket is not available.
		

		registry.addEndpoint("/hassantuk").setAllowedOrigins("*");

		return;
	}

	@Bean
	public UserPresenceService presenceChannelInterceptor() {
		return new UserPresenceService();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.setInterceptors(presenceChannelInterceptor());
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		registration.taskExecutor().corePoolSize(OUTBOUND_CHANNEL_CORE_POOL_SIZE);
		registration.setInterceptors(presenceChannelInterceptor());
	}
	 protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		    messages.simpDestMatchers("/*").authenticated();
	 }

	// /**
	// * Configure the message broker.
	// */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		if (brokerEnabled) {
			StompBrokerRelayRegistration brokerRegistration = config
					.enableStompBrokerRelay("/topic", Queue.SMAC_SENT, Queue.SMAC_SENT_V2).setSystemLogin(brokerLogin)
					.setSystemPasscode(brokerPassword)
					.setRelayHost(brokerHost);
			config.setApplicationDestinationPrefixes(Queue.SMAC_RECEIVED);
			if (!brokerVirtualHost.equals("")) {
				brokerRegistration.setVirtualHost(brokerVirtualHost);
			}
		} else {
			config.setApplicationDestinationPrefixes(Queue.SMAC_RECEIVED);
			config.enableSimpleBroker("/topic", Queue.SMAC_SENT, Queue.SMAC_SENT_V2);
		}
		return;
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
		registration.setSendTimeLimit(15 * 1000).setSendBufferSizeLimit(512 * 1024);
	}

	// @Bean
	// public ServletServerContainerFactoryBean createWebSocketContainer() {
	// ServletServerContainerFactoryBean container = new
	// ServletServerContainerFactoryBean();
	// container.setMaxTextMessageBufferSize(MESSAGE_BUFFER_SIZE);
	// container.setMaxBinaryMessageBufferSize(MESSAGE_BUFFER_SIZE);
	// container.setMaxSessionIdleTimeout(HOUR_IN_MILLIS);
	// container.setAsyncSendTimeout(SECOND_IN_MILLIS);
	// return container;
	// }

}
