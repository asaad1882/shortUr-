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


import com.sap.iotservices.api.models.Device;

import feign.FeignException;



@FeignClient(name="coreAPI", url = "${coreAPI.url}",fallback=CoreProxyClient.CoreClientFallback.class)
public interface CoreProxyClient {
	@RequestMapping(value = "/devices", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	 public Device createDevice(@RequestBody Device device) ;
	@RequestMapping(value = "/devices/{deviceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public Device readDevice(@PathVariable("deviceId") String deviceId) ;
	
	@RequestMapping(value = "/devices/{deviceId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	 public Device updateDevice(@RequestBody Device device,@PathVariable("deviceId") String deviceId) ;

	 @Component
	    public static class CoreClientFallback implements CoreProxyClient{
		 private static final Logger LOGGER = LoggerFactory.getLogger(CoreClientFallback.class);
		 private final Throwable cause;
		 public CoreClientFallback() {
		     this.cause=null;
		    }

		    public CoreClientFallback(Throwable cause) {
		      this.cause = cause;
		    }

		public Device createDevice(Device device) {
			LOGGER.error("Core createDevice is not reachable "+cause);	
			 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
	            	LOGGER.error("Core createDevice is not reachable");	            	
	         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
	            	LOGGER.error("Core internal error",cause);	            	
	         }
					
			return null;
		}

		public Device updateDevice(Device device,String deviceId) {
			LOGGER.error("Core updateDevice is not reachable "+cause);	
			 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
	            	LOGGER.error("Core createDevice is not reachable");	            	
	         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
	            	LOGGER.error("Core internal error",cause);	            	
	         }
			return null;
		}

		public Device readDevice(String deviceId) {
			LOGGER.error("Core readDevice is not reachable "+cause);	
			 if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
	            	LOGGER.error("Core createDevice is not reachable");	            	
	         }else  if (cause instanceof FeignException && ((FeignException) cause).status() == 500) {
	            	LOGGER.error("Core internal error",cause);	            	
	         }
//			Device device=new Device();
//			CustomProperty customProperty = new CustomProperty();
//			customProperty.setKey(APIConstant.ATE_STATE);
//			customProperty.setValue(Integer.toString(ATEState.PROVISIONED.getValue()));
//			CustomProperty buildingCAT = new CustomProperty();
//			buildingCAT.setKey(APIConstant.BUILDING_CLS);
//			buildingCAT.setValue(Integer.toString(1));
//			CustomProperty[] customProperties=new CustomProperty[] { customProperty,buildingCAT };
//			device.setCustomProperties(customProperties);			
			return null;
		}
	  
	        

	    }
}

