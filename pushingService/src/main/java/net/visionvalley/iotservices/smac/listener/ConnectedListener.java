package net.visionvalley.iotservices.smac.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class ConnectedListener implements ApplicationListener<SessionConnectedEvent> {

    private final SimpMessagingTemplate messagingTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedListener.class);

    @Autowired
    public ConnectedListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
    	LOGGER.info("Event Connected:"+event.getUser().getName()+event.getMessage().toString());
        messagingTemplate.convertAndSend( "/smacAPI/alarms", "Last known error count");
    }
}
