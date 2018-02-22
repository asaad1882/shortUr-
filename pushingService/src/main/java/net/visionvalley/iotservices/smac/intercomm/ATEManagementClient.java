package net.visionvalley.iotservices.smac.intercomm;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import net.visionvalley.iotservices.smac.dto.AckMessage;
import net.visionvalley.iotservices.smac.dto.ResponseDTO;





@FeignClient("ate-management-service")
public interface ATEManagementClient {
	@RequestMapping(value = "/confirmation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO confirmation(@RequestBody AckMessage ackMessage);
}
