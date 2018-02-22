package net.visionvalley.iotservices.smac.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;

import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

import net.visionvalley.iotservices.smac.queuesconfiguarions.Queue;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer{
	@Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		 messages
         .nullDestMatcher().authenticated() 
         .simpSubscribeDestMatchers("/user/queue/errors").permitAll() 
         .simpDestMatchers(Queue.SMAC_SENT_AUTHORIZED_PATTERN).hasRole("USER") 
         .simpDestMatchers(Queue.SMAC_SENT_V2_AUTHORIZED_PATTERN).hasRole("USER") 
         .simpSubscribeDestMatchers(Queue.SMAC_RECEIVED_AUTHORIZED_PATTERN).hasRole("USER") 
         .simpTypeMatchers(SimpMessageType.MESSAGE, SimpMessageType.SUBSCRIBE).denyAll() 
         .anyMessage().denyAll();

    }
	 @Override
	    protected boolean sameOriginDisabled() {
	        return true;
	    }
}
