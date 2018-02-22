package net.visionvalley.iot.smac.atemanagement.intercomm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import feign.FeignException;
import net.visionvalley.iot.smac.atemanagement.dto.Device;




@FeignClient("deviceManagement-service")
public interface DPMInterface {
	 @RequestMapping(value = "/provisioning", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody Device provisioning(@RequestBody Device device);
	 @Component
	    public static class DPMInterfaceFallback implements DPMInterface{
		 private static final Logger LOGGER = LoggerFactory.getLogger(DPMInterfaceFallback.class);
		 private final Throwable cause;
		 public DPMInterfaceFallback() {
		     this.cause=null;
		    }

		    public DPMInterfaceFallback(Throwable cause) {
		      this.cause = cause;
		    }

		public Device provisioning(Device device) {
			LOGGER.error("Validation module provisioning is not reachable "+cause);	
			 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
	            	LOGGER.error("Validation module provisioning is not reachable");	            	
	         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
	            	LOGGER.error("Validation module provisioning internal error",cause);	            	
	         }
				
			return null;
		}
		 
	 }
}
