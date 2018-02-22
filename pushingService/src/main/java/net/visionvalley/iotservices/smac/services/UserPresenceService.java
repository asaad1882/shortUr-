package net.visionvalley.iotservices.smac.services;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

import net.visionvalley.iotservices.smac.rest.HomeController;

import static org.springframework.messaging.simp.stomp.StompHeaderAccessor.STOMP_LOGIN_HEADER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Component
public class UserPresenceService extends ChannelInterceptorAdapter {
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserPresenceService.class);

  @Override
  public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    StompHeaderAccessor stompDetails = StompHeaderAccessor.wrap(message);
   


    if(stompDetails.getCommand() == null) { return; }
  
    switch(stompDetails.getCommand()) {
      case CONNECT:  
    	  LOGGER.info("Connect "+stompDetails.getVersion()+ message);
    	  break;
      case CONNECTED:
    	  LOGGER.info("CONNECTED "+stompDetails.getVersion()+ message);  	
       
        break;
      case DISCONNECT:
    	  LOGGER.info("DISCONNECT "+stompDetails.getVersion()+ message);    	 
        break;
      case SUBSCRIBE:
    	  LOGGER.info("SUBSCRIBE "+ message);
    	 
      break; 
      case UNSUBSCRIBE:
    	  System.out.println("UNSUBSCRIBE"+stompDetails.getSessionId()+" :"+stompDetails.getSubscriptionId()); 
      break; 
      case ACK:
    	  System.out.println("ACK"+stompDetails.getSessionId()+" :"+stompDetails.getMessageId()); 
      break; 
      case MESSAGE:
    	  LOGGER.info("MESSAGE "+stompDetails.getVersion()+ stompDetails.getMessage());
       break; 
      default:
        break;
    }
  }
  @Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		return message;
	}


  
}