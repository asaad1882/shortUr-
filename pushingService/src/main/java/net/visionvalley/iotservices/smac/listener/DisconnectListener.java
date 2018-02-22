package net.visionvalley.iotservices.smac.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import net.visionvalley.iotservices.smac.services.ActiveUserService;

@Component
public class DisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

    
    private static final Logger LOGGER = LoggerFactory.getLogger(DisconnectListener.class);

    @Autowired
	private ActiveUserService activeUserService; 
	

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
    	LOGGER.info("Event Disconnected:"+event.getUser().getName()+event.getMessage().toString());
    	activeUserService.unMark(event.getUser().getName());
       
    }
}
