package net.visionvalley.iotservices.smac.rest;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.visionvalley.iotservices.smac.dto.AckMessage;
import net.visionvalley.iotservices.smac.dto.MessageDTO;
import net.visionvalley.iotservices.smac.entities.Activity;
import net.visionvalley.iotservices.smac.queuesconfiguarions.Queue;
import net.visionvalley.iotservices.smac.services.ActiveUserService;
import net.visionvalley.iotservices.smac.services.ActivityService;




@RestController
public class HomeController {
	 @Autowired
	  private SimpMessagingTemplate messagingTemplate;
	 @Autowired
	 private ActivityService activityService;
	 @Autowired
	 private ActiveUserService activeUserService; 
	 
	 private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	 private int number=0;
	

	  @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String index(){
        return "Hello world";
    }

    
    @RequestMapping(value = "/alarmActions", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> alarmActions(@RequestBody MessageDTO message) {     
    	List<String> activeUsers = activeUserService.getActiveUsers();
    	for (String user : activeUsers) {
    		messagingTemplate.convertAndSendToUser(user, Queue.SMAC_SENT, message);
    	}
  	   return new ResponseEntity<>(HttpStatus.OK);

    }
    @RequestMapping(value = "/provisionActions", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> provisionActions(@RequestBody List<MessageDTO> messageDTOs) {     
    	for (MessageDTO messageDTO : messageDTOs) {
    		  messagingTemplate.convertAndSend(Queue.SMAC_SENT_V2, messageDTO);
    		  LOGGER.info("Message To be sent :"+messageDTO);
		}
    
    	
  	   return new ResponseEntity<>(HttpStatus.OK);

    }
    /**
     * POST  /some-action  -> do an action.
     * 
     * After the action is performed will be notified UserA.
     */
    @MessageMapping("/alarmsAck")
    
    public void provisionAck(AckMessage ackMessage) {
    	LOGGER.info("message"+ackMessage);
    	
    	
  	  
      
    }
   
    @RequestMapping(value = "/getActivities}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   
    public List<Activity> getActivities() {
    	return   activityService.findAll();
    }
    @Scheduled(fixedRate = 5000)
    public void run() {
    	LOGGER.info("start sending message");
    	List<String> activeUsers = activeUserService.getActiveUsers();
    	//for (String user : activeUsers) {
    		MessageDTO messageDTO=new MessageDTO();
        	if(number%2==0)
        	messageDTO.setMessageType("MAINTENANCE");
        	else
        		messageDTO.setMessageType("FIRE ALARM");
        	number++;
        	messageDTO.setAteTagNo("AUH-1001-234561");
        	messageDTO.setErrorCode(110);
        	messageDTO.setAteMode(0);
        	messageDTO.setAlarmStatus(1);
        	messageDTO.setExtraInfo("000(Test)");
        	Date date=new Date();
        	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        	String currentData=dateFormat.format(date);
            try {
    			messageDTO.setTimestamp(dateFormat.parse(currentData));
    		} catch (ParseException e) {    			
    			e.printStackTrace();
    		}
           // LOGGER.info("Message To be sent :"+messageDTO+ " To User:"+user);
            Map<String,Object> headers=new HashMap<>();
      	    headers.put("persistent", "true");
            messagingTemplate.convertAndSend(Queue.SMAC_SENT, messageDTO,headers);
            messagingTemplate.convertAndSend(Queue.SMAC_SENT_V2,messageDTO,headers);
			
		//}
    	
    	
    }
}
