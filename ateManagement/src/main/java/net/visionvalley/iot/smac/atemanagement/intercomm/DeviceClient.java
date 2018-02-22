package net.visionvalley.iot.smac.atemanagement.intercomm;

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
import net.visionvalley.iot.smac.atemanagement.dto.Device;
import net.visionvalley.iot.smac.atemanagement.intercomm.CoreProxyClient.CoreClientFallback;





@FeignClient(name="device-service",fallback =DeviceClient.DeviceClientFallback.class)
public interface DeviceClient {
	@RequestMapping(value = "/cache/device/{macAddress}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    Device show(@PathVariable("macAddress") String id);
	 @RequestMapping(value = "/cache/device", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody Device update(@RequestBody Device device);
	 @RequestMapping(value = "/cache/device", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public void delete(Device device);
	 @Component
	    public static class DeviceClientFallback implements DeviceClient{
		 private static final Logger LOGGER = LoggerFactory.getLogger(DeviceClientFallback.class);
		 private final Throwable cause;
		 public DeviceClientFallback() {
		     this.cause=null;
		    }

		    public DeviceClientFallback(Throwable cause) {
		      this.cause = cause;
		    }

		public Device show(String id) {			
			LOGGER.error("Core createDevice is not reachable "+cause);	
			 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
	            	LOGGER.error("Core createDevice is not reachable");	            	
	         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
	            	LOGGER.error("Core internal error",cause);	            	
	         }
					
			return null;
		}

		public Device update(Device device) {			
			LOGGER.error("Core createDevice is not reachable "+cause);	
			 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
	            	LOGGER.error("Core createDevice is not reachable");	            	
	         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
	            	LOGGER.error("Core internal error",cause);	            	
	         }
					
			return null;
		}

		public void delete(Device device) {
			LOGGER.error("Core createDevice is not reachable "+cause);	
			 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
	            	LOGGER.error("Core createDevice is not reachable");	            	
	         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
	            	LOGGER.error("Core internal error",cause);	            	
	         }					
			
			
		}
		 
	 }
}

