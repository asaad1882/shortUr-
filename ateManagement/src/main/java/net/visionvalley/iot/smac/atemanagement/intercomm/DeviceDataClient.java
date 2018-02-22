package net.visionvalley.iot.smac.atemanagement.intercomm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import feign.FeignException;
import net.visionvalley.iot.smac.atemanagement.dto.MessageDTO;
import net.visionvalley.iot.smac.atemanagement.dto.ProvisionMessage;







@FeignClient(name="deviceData-service",fallback=DeviceDataClient.DeviceDataClientFallBack.class)
public interface DeviceDataClient {
	
	 @RequestMapping(value = "/device/heartbeatFailure", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody MessageDTO updateHeartBeat(@PathVariable("atetagno") String ateTagNo); 
	 @RequestMapping(value = "/deviceData/provisionMessage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<MessageDTO> provisionMessage(@RequestBody ProvisionMessage provisionDTO);
	 @Component
	 public static class DeviceDataClientFallBack implements DeviceDataClient{
		 private static final Logger LOGGER = LoggerFactory.getLogger(DeviceDataClientFallBack.class);
		 private final Throwable cause;
		 public DeviceDataClientFallBack() {
		     this.cause=null;
		    }

		    public DeviceDataClientFallBack(Throwable cause) {
		      this.cause = cause;
		    }
		public MessageDTO updateHeartBeat(String ateTagNo) {
			LOGGER.error("Message construction updateHeartBeat is not reachable "+cause);	
			 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
	            	LOGGER.error("Message construction updateHeartBeat is not reachable");	            	
	         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
	            	LOGGER.error("Message construction updateHeartBeat internal error",cause);	            	
	         }
					
			return null;
		}

		public List<MessageDTO> provisionMessage(ProvisionMessage provisionDTO) {
			LOGGER.error("Message construction provisionMessage is not reachable "+cause);	
			 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
	            	LOGGER.error("Message construction provisionMessage is not reachable");	            	
	         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
	            	LOGGER.error("Message construction provisionMessage internal error",cause);	            	
	         }
			
			return null;
		}
		 
	 }

}
