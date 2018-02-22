package net.visionvalley.iot.smac.atemanagement.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.visionvalley.iot.smac.atemanagement.domains.ATEHistory;
import net.visionvalley.iot.smac.atemanagement.dto.ATEInstallationDTO;
import net.visionvalley.iot.smac.atemanagement.dto.ATEPairingDTO;
import net.visionvalley.iot.smac.atemanagement.dto.AckMessage;
import net.visionvalley.iot.smac.atemanagement.dto.AteDTO;
import net.visionvalley.iot.smac.atemanagement.dto.AteStatusDTO;
import net.visionvalley.iot.smac.atemanagement.dto.MessageDTO;
import net.visionvalley.iot.smac.atemanagement.dto.ProvisionDTO;
import net.visionvalley.iot.smac.atemanagement.dto.ResponseDTO;

import net.visionvalley.iot.smac.atemanagement.service.ATEManagementService;
import net.visionvalley.iot.smac.atemanagement.utils.ATEState;

@RestController("deviceAPI")
@Api(value = "/", description = "Operations about ATE Lifecycle", consumes = "application/json", produces = "application/json")
public class ATEManagementController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(ATEManagementController.class);
	@Autowired
	private ATEManagementService ateManagementService;

	@ApiOperation(value = "As part of provisioning of new ATEs, ICT CRM will call this method to provision the new ATE to IoT Platform.", response = ResponseDTO.class, responseContainer = "ResponseDTO")
	@RequestMapping(value = "/device", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDTO deviceProvision(@RequestBody AteDTO ateDTO) {
		LOGGER.info("add device is called with :"+ateDTO);
		ResponseDTO responseDTO= ateManagementService.save(ateDTO);
		LOGGER.info("add device is returned for :"+ateDTO+" with respone"+responseDTO);
		return responseDTO;
	}

	@ApiOperation(value = "As part of ATE life cycle, ICT CRM will require to change ATE status, and at that time it will update IoT Platform with the new status using this method.", response = ResponseDTO.class, responseContainer = "ResponseDTO")
	@RequestMapping(value = "/device", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDTO deviceUpdate(@RequestBody AteStatusDTO ateDTO) {
		LOGGER.info("update device is called with :"+ateDTO);
		ResponseDTO responseDTO=ateManagementService.update(ateDTO);
		LOGGER.info("update device is returned for :"+ateDTO+" with respone"+responseDTO);
		return responseDTO;
	}
	

	@ApiOperation(value = "As part of ATE life cycle, ICT CRM will require to unPair the ATE from the attached customer information, and change its status to Provisioned and at that time it will update IoT Platform by calling this method.", response = ResponseDTO.class, responseContainer = "ResponseDTO")
	@RequestMapping(value = "/deviceUnPair", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDTO deviceUnPair(@QueryParam(value = "ateTagNo") String ateTagNo,@QueryParam(value="transactionId") @DefaultValue("-1") long transactionId) {
		LOGGER.info("device UnPair is called with :"+ateTagNo+ " transactionId:"+transactionId);
		AteStatusDTO ateStatusDTO = new AteStatusDTO();
		ateStatusDTO.setAteTagNo(ateTagNo);
		ateStatusDTO.setStatus(ATEState.UNPAIRED.getValue());
		ResponseDTO responseDTO= ateManagementService.update(ateStatusDTO);
		LOGGER.info("device UnPair is returned for :"+ateTagNo+ " transactionId:"+transactionId +" with respone:"+responseDTO);
		return responseDTO;
	}

	@ApiOperation(value = "As part of paring of new ATEs, ICT CRM will call this method in the IoT Platform to change ATE status to paring and attach pairing information (ATE Tag No, Input Numbers, Emirate, Area Code).", response = ResponseDTO.class, responseContainer = "ResponseDTO")
	@RequestMapping(value = "/devicePairing", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDTO devicePairing(@RequestBody ATEPairingDTO ateDTO) {
		LOGGER.info("device Pair is called with :"+ateDTO);
		ResponseDTO responseDTO= ateManagementService.update(ateDTO);
		LOGGER.info("device Pair is returned for :"+ateDTO+" with respone"+responseDTO);
		return responseDTO;
	}

	@ApiOperation(value = "After installation Ate at customer’s building, Technician will update device status at Injazat CRM in addition to number of inputs, which in turn will call this method in IoT Platform through ICT CRM, to change ATE status to Installed and set number of inputs ", response = ResponseDTO.class, responseContainer = "ResponseDTO")
	@RequestMapping(value = "/deviceInstallation", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDTO deviceInstallation(@RequestBody ATEInstallationDTO ateDTO) {
		LOGGER.info("device Installation is called with :"+ateDTO);
		ResponseDTO responseDTO= ateManagementService.update(ateDTO);
		LOGGER.info("device Installation is returned for :"+ateDTO+" with respone"+responseDTO);
		return responseDTO;
	}

	@RequestMapping(value = "/ateHistory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ATEHistory> getHistory() {
		return ateManagementService.getHistory();
	}

	@ApiOperation(value = "After the time specified per building category and validting number of input againts the received evt-code, IoT system will use this method to change state of ATE to PREACRIVATED and send provision message to vidsys", response = ResponseDTO.class, responseContainer = "ResponseDTO")
	@RequestMapping(value = "/provisioningResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO provisionResult(@RequestBody ProvisionDTO provisionDTO) {
		LOGGER.info("Provision Result is called with :"+provisionDTO);
		ResponseDTO responseDTO= ateManagementService.provisionSucceed(provisionDTO);
		LOGGER.info("Provision Result is returned for :"+provisionDTO+" with respone"+responseDTO);
		return responseDTO;
	}
	@ApiOperation(value = "Confirm Provision message status", response = ResponseDTO.class, responseContainer = "ResponseDTO")
	@RequestMapping(value = "/confirmation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO confirmation(@RequestBody AckMessage ackMessage) {
		LOGGER.info("confirmation is called with :"+ackMessage);
		ResponseDTO responseDTO= ateManagementService.confirmProvision(ackMessage);
		LOGGER.info("confirmation is called with :"+ackMessage+" with respone"+responseDTO);
		return responseDTO;
	}
	@ApiOperation(value = "Cache Alarms", response = ResponseDTO.class, responseContainer = "ResponseDTO")
	@RequestMapping(value = "/cacheAlarm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cacheAlarm(@RequestBody MessageDTO  messageDTO) {
		LOGGER.info("cacheAlarm is called with :"+messageDTO);		
		 ateManagementService.cacheMessages(messageDTO);
		
		
	}
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		LOGGER.info("Excecption :"+ex);
		ResponseDTO apiError = new ResponseDTO(false,-200,ex.getMessage());
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.OK);
	}
}
