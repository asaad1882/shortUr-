package net.visionvalley.iotservices.smac.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class UnsubscribeListener implements ApplicationListener<SessionUnsubscribeEvent> {

    private final SimpMessagingTemplate messagingTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(UnsubscribeListener.class);

    @Autowired
    public UnsubscribeListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
    	LOGGER.info("Unsubscribe Event:"+event.getUser().getName()+event.getMessage().toString());
        messagingTemplate.convertAndSend( "/smacAPI/alarms", "Last known error count");
    }
}
