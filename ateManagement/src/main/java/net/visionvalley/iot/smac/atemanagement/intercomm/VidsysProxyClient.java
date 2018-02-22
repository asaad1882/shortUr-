package net.visionvalley.iot.smac.atemanagement.intercomm;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.FeignException;
import net.visionvalley.iot.smac.atemanagement.dto.MessageDTO;
import net.visionvalley.iot.smac.atemanagement.intercomm.DPMInterface.DPMInterfaceFallback;

@FeignClient(name="VidsysProxy",fallback =VidsysProxyClient.VidsysClientFallback.class)
public interface VidsysProxyClient {
	@RequestMapping(value = "/alarmActions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> alarmActions(@RequestBody MessageDTO message) ;
	@RequestMapping(value = "/provisionActions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<HttpStatus> provisionActions(@RequestBody List<MessageDTO> messageDTOs) ;
	 @Component
	    public static class VidsysClientFallback implements VidsysProxyClient{
		 private static final Logger LOGGER = LoggerFactory.getLogger(DPMInterfaceFallback.class);
		 private final Throwable cause;
		 public VidsysClientFallback() {
		     this.cause=null;
		    }

		    public VidsysClientFallback(Throwable cause) {
		      this.cause = cause;
		    }

	  
	        
	        public ResponseEntity<HttpStatus> provisionActions(@RequestBody List<MessageDTO> messageDTOs) {
	        	LOGGER.error("Vidsys push API provisionActions is not reachable "+cause);	
				 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
		            	LOGGER.error("Vidsys push API provisionActions is not reachable");	            	
		         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
		            	LOGGER.error("Vidsys push API provisionActions internal error",cause);	            	
		         }
					
	            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	        }
	        public ResponseEntity<HttpStatus> alarmActions(@RequestBody MessageDTO message){
	        	LOGGER.error("Vidsys push API alarmActions is not reachable "+cause);	
				 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
		            	LOGGER.error("Vidsys push API alarmActions is not reachable");	            	
		         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
		            	LOGGER.error("Vidsys push API alarmActions internal error",cause);	            	
		         }
	        	 return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	        	
	        }
	    }
}

