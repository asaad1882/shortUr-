package net.visionvalley.iotservices.smac.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Component
public class ConnectListener implements ApplicationListener<SessionConnectEvent> {

    private final SimpMessagingTemplate messagingTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectListener.class);

    @Autowired
    public ConnectListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
    	LOGGER.info("In here Event:"+event.getUser().getName()+event.getMessage().toString());
        messagingTemplate.convertAndSend( "/smacAPI/alarms", "Last known error count");
    }
}
