package net.visionvalley.iotservices.smac.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import net.visionvalley.iotservices.smac.entities.Activity;
import net.visionvalley.iotservices.smac.entities.User;
import net.visionvalley.iotservices.smac.services.ActiveUserService;
import net.visionvalley.iotservices.smac.services.ActivityService;

@Component
public class SubscribeListener implements ApplicationListener<SessionSubscribeEvent> {
	@Autowired
	private  ActivityService activityService;
	@Autowired
	private ActiveUserService activeUserService; 
	
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeListener.class);

    
    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
    	LOGGER.info("Subscribe Event:"+event.getUser().getName()+event.getMessage().toString());
//    	Activity activity=new Activity();
//    	
//    	activity.setUser((User)event.getUser());
//    	activity.setRequestMethod("SUBSCRIBE");
//    	activityService.save(activity);
    	activeUserService.mark(event.getUser().getName());
        
    }
}
