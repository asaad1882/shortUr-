package net.visionvalley.iot.smac.atemanagement.service;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;

import javax.servlet.UnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.sap.iotservices.api.models.CustomProperty;
import com.sap.iotservices.ws.info.OperationStatus;


import net.visionvalley.iot.smac.atemanagement.domains.ATEHistory;
import net.visionvalley.iot.smac.atemanagement.domains.ProvisionTransactions;
import net.visionvalley.iot.smac.atemanagement.dto.ATEInstallationDTO;
import net.visionvalley.iot.smac.atemanagement.dto.ATEPairingDTO;
import net.visionvalley.iot.smac.atemanagement.dto.AckMessage;
import net.visionvalley.iot.smac.atemanagement.dto.AlarmMessageDTO;
import net.visionvalley.iot.smac.atemanagement.dto.AteDTO;
import net.visionvalley.iot.smac.atemanagement.dto.AteStatusDTO;
import net.visionvalley.iot.smac.atemanagement.dto.Device;
import net.visionvalley.iot.smac.atemanagement.dto.HeartBeatDTO;
import net.visionvalley.iot.smac.atemanagement.dto.MessageDTO;
import net.visionvalley.iot.smac.atemanagement.dto.ProvisionDTO;
import net.visionvalley.iot.smac.atemanagement.dto.ProvisionMessage;
import net.visionvalley.iot.smac.atemanagement.dto.ProvisionMessageDTO;
import net.visionvalley.iot.smac.atemanagement.dto.ResponseDTO;
import net.visionvalley.iot.smac.atemanagement.intercomm.CoreProxyClient;
import net.visionvalley.iot.smac.atemanagement.intercomm.DPMInterface;
import net.visionvalley.iot.smac.atemanagement.intercomm.DeviceClient;
import net.visionvalley.iot.smac.atemanagement.intercomm.DeviceDataClient;
import net.visionvalley.iot.smac.atemanagement.intercomm.VidsysProxyClient;
import net.visionvalley.iot.smac.atemanagement.reporsitry.ATEHistoryRepo;
import net.visionvalley.iot.smac.atemanagement.reporsitry.ProvisionTransactionRepo;
import net.visionvalley.iot.smac.atemanagement.utils.APIConstant;
import net.visionvalley.iot.smac.atemanagement.utils.ATEState;
import net.visionvalley.iot.smac.atemanagement.utils.TransactionState;

@Service
public class ATEManagementService {
	@Autowired
	private DeviceClient deviceClient;
	@Autowired
	private CoreProxyClient coreProxyClient;
	@Autowired
	private ExecutorService executorService;
	@Autowired
	private ATEHistoryRepo ateHistoryRepo;
	@Autowired
	private DPMInterface dpmInterface;
	@Autowired
	private DeviceDataClient deviceDataClient;
	@Autowired
	private VidsysProxyClient vidsysProxyClient;
	@Autowired
	private ProvisionTransactionRepo provisionTransactionRepo;
	
	
	@Retryable(value = {SocketTimeoutException.class, UnavailableException.class,HystrixRuntimeException.class,NullPointerException.class
		    },
		     maxAttemptsExpression = "#{${max.read.attempts}}")
		public com.sap.iotservices.api.models.Device coreOperation(com.sap.iotservices.api.models.Device coreDevice) {
			 return coreProxyClient.createDevice(coreDevice);
			 	 
		}


	public ResponseDTO save(AteDTO ateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Device device = deviceClient.show(ateDTO.getMacAddress());
		if (device != null && device.getDeviceId() != null) {
			responseDTO.setOperationSucceeded(false);
			responseDTO.setErrorCode(-103);
			responseDTO.setErrorDescription("Device Already exist");
		} else {
			com.sap.iotservices.api.models.Device coreDevice = new com.sap.iotservices.api.models.Device();
			coreDevice.setGatewayId(Integer.toString(ateDTO.getNetworkProtoclIdentifier()));
			coreDevice.setAlternateId(ateDTO.getMacAddress());
			coreDevice.setCustomProperties(createCustomProperty(ateDTO));
			coreDevice.setName(ateDTO.getMacAddress());
			coreDevice = coreOperation(coreDevice);
			Device cacheDevice = new Device();
			cacheDevice.setDeviceClass(Integer.toString(ateDTO.getBuildingClass()));
			cacheDevice.setMacAddress(ateDTO.getMacAddress());
			cacheDevice.setDeviceId(coreDevice.getId());
			cacheDevice.setStatus(Integer.toString(ATEState.PROVISIONED.getValue()));
			deviceClient.update(cacheDevice);
			responseDTO.setErrorCode(0);
			responseDTO.setErrorDescription("SUCCESS");
			responseDTO.setOperationSucceeded(true);

			createHistory(cacheDevice);
		}
		return responseDTO;
	}

	private CustomProperty[] customizeCustomProperties(CustomProperty[] customProperties, int state) {
		for (CustomProperty customProperty : customProperties) {
			if (APIConstant.ATE_STATE.equalsIgnoreCase(customProperty.getKey())) {
				customProperty.setValue(Integer.toString(state));
			}
		}
		return customProperties;
	}

	private CustomProperty[] createCustomProperty(AteDTO ateDTO) {
		CustomProperty customProperty = new CustomProperty();
		customProperty.setKey(APIConstant.ATE_STATE);
		customProperty.setValue(Integer.toString(ATEState.PROVISIONED.getValue()));
		CustomProperty buildingCAT = new CustomProperty();
		buildingCAT.setKey(APIConstant.BUILDING_CLS);
		buildingCAT.setValue(Integer.toString(ateDTO.getBuildingClass()));
		return new CustomProperty[] { customProperty, buildingCAT };
	}

	private CustomProperty[] addCustomProperties(CustomProperty[] customProperties, Map<String, String> prop) {
		List<CustomProperty> properties = Arrays.asList(customProperties);
		List<CustomProperty> newProp = new ArrayList<CustomProperty>();
		Iterator<Entry<String, String>> it = prop.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			CustomProperty customProperty = new CustomProperty((String) pair.getKey(), (String) pair.getValue());
			newProp.add(customProperty);
		}
		for (CustomProperty customProperty : properties) {
			newProp.add(customProperty);
		}
		return newProp.toArray(new CustomProperty[newProp.size()]);
	}

	public ResponseDTO provisionSucceed(ProvisionDTO provisionDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (provisionDTO != null) {
			if (provisionDTO.getAlarmMessageDTOs() != null) {
				Device device = deviceClient.show(provisionDTO.getAteTagNo());
				com.sap.iotservices.api.models.Device coreDevice = coreProxyClient.readDevice(device.getDeviceId());
				CustomProperty[] customProperties = coreDevice.getCustomProperties();
				customizeCustomProperties(customProperties, ATEState.PREACRIVATED.getValue());
				deleteTagNum(customProperties);
				coreProxyClient.updateDevice(coreDevice, device.getDeviceId());
				device.setStatus(Integer.toString(ATEState.PREACRIVATED.getValue()));
				ProvisionMessage provisionMessage = new ProvisionMessage();
				provisionMessage.setAteTagNo(provisionDTO.getAteTagNo());
				provisionMessage.setNumberOfInput(provisionDTO.getNumberOfInput());
				provisionMessage.setAlarmMessageDTOs(provisionDTO.getAlarmMessageDTOs());
				List<MessageDTO> messageDTOs = deviceDataClient.provisionMessage(provisionMessage);
				
				vidsysProxyClient.provisionActions(messageDTOs);
				createHistory(device);
				responseDTO.setErrorCode(0);
				responseDTO.setErrorDescription("SUCCESS");
				responseDTO.setOperationSucceeded(true);
				cacheMessages(provisionDTO.getAlarmMessageDTOs(), Long.parseLong(device.getDeviceId()));
			} else {
				AteStatusDTO ateStatusDTO = new AteStatusDTO();
				ateStatusDTO.setAteTagNo(provisionDTO.getAteTagNo());
				ateStatusDTO.setStatus(ATEState.FAILEDINSTALLATION.getValue());
				update(ateStatusDTO);
			}
		}
		return responseDTO;

	}

	public ResponseDTO update(AteStatusDTO ateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Device device = deviceClient.show(ateDTO.getAteTagNo());
		if (device == null) {
			responseDTO.setOperationSucceeded(false);
			responseDTO.setErrorCode(-100);
			responseDTO.setErrorDescription("Device not found");
		} else {
			if ((!Integer.toString(ATEState.ACTIVE.getValue()).equals(device.getStatus())
					|| !Integer.toString(ATEState.FAILEDACTIVATION.getValue()).equals(device.getStatus())
					|| !Integer.toString(ATEState.FAILURE.getValue()).equals(device.getStatus()))
					&& ateDTO.getStatus() == ATEState.DEACTIVATED.getValue()) {
				responseDTO.setOperationSucceeded(false);
				responseDTO.setErrorCode(-102);
				responseDTO.setErrorDescription("Device is not in proper state");
			} else {
				if (ateDTO.getStatus() == ATEState.DEACTIVATED.getValue()) {
					com.sap.iotservices.api.models.Device coreDevice = coreProxyClient.readDevice(device.getDeviceId());
					coreDevice.setStatus(OperationStatus.commissioning);
					CustomProperty[] customProperties = coreDevice.getCustomProperties();
					customizeCustomProperties(customProperties, ATEState.DEACTIVATED.getValue());
					coreProxyClient.updateDevice(coreDevice, device.getDeviceId());
					deviceClient.delete(device);
				} else if (ateDTO.getStatus() == ATEState.UNPAIRED.getValue()) {
					com.sap.iotservices.api.models.Device coreDevice = coreProxyClient.readDevice(device.getDeviceId());
					CustomProperty[] customProperties = coreDevice.getCustomProperties();
					customizeCustomProperties(customProperties, ATEState.PROVISIONED.getValue());
					deleteTagNum(customProperties);
					coreProxyClient.updateDevice(coreDevice, device.getDeviceId());
				} else if (ateDTO.getStatus() == ATEState.FAILURE.getValue()) {
					MessageDTO messageDTO = deviceDataClient.updateHeartBeat(ateDTO.getAteTagNo());
					vidsysProxyClient.alarmActions(messageDTO);
					device.setStatus(Integer.toString(ATEState.FAILURE.getValue()));
					statusUpdate(device);

				} else if (ateDTO.getStatus() == ATEState.PREACRIVATED.getValue()
						&& Integer.parseInt(device.getStatus()) == ATEState.FAILEDACTIVATION.getValue()) {
					AckMessage ackMessage = new AckMessage();
					ackMessage.setAteTagNo(ateDTO.getAteTagNo());
					ackMessage.setTransactionId(Integer.parseInt(device.getDeviceId()));

					changeState(ackMessage, ATEState.PREACRIVATED.getValue(), TransactionState.PENDING.getValue(),
							device);
				} else {
					device.setStatus(Integer.toString(ateDTO.getStatus()));
					statusUpdate(device);
				}

				
				responseDTO.setErrorCode(0);
				responseDTO.setErrorDescription("SUCCESS");
				responseDTO.setOperationSucceeded(true);
			}
		}
		return responseDTO;
	}

	private void deleteTagNum(CustomProperty[] customProperties) {
		for (CustomProperty customProperty : customProperties) {
			if (APIConstant.ATE_TAG_NO.equalsIgnoreCase(customProperty.getKey())) {
				customProperty.setValue("");
			}
		}

	}

	public ResponseDTO update(ATEInstallationDTO ateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Device device = deviceClient.show(ateDTO.getAteTagNo());
		if (device == null) {
			responseDTO.setOperationSucceeded(false);
			responseDTO.setErrorCode(-100);
			responseDTO.setErrorDescription("Device not found");
		} else {
			if (!Integer.toString(ATEState.PAIRED.getValue()).equals(device.getStatus())
					|| !Integer.toString(ATEState.FAILEDINSTALLATION.getValue()).equals(device.getStatus())) {
				responseDTO.setOperationSucceeded(false);
				responseDTO.setErrorCode(-102);
				responseDTO.setErrorDescription("Device is not in proper state");
			} else {
				com.sap.iotservices.api.models.Device coreDevice = coreProxyClient.readDevice(device.getDeviceId());
				CustomProperty[] customProperties = coreDevice.getCustomProperties();
				customizeCustomProperties(customProperties, ATEState.INSTALLED.getValue());
				Map<String, String> prop = new HashMap<String, String>();
				prop.put(APIConstant.ATE_NUM_IMPUT, Integer.toString(ateDTO.getNumberOfInputs()));
				CustomProperty[] properties = addCustomProperties(customProperties, prop);
				coreDevice.setCustomProperties(properties);
				coreProxyClient.updateDevice(coreDevice, device.getDeviceId());
				device.setStatus(Integer.toString(ATEState.INSTALLED.getValue()));
				device.setActualNumberOfInputs(ateDTO.getNumberOfInputs());
				deviceClient.update(device);
				responseDTO.setErrorCode(0);
				responseDTO.setErrorDescription("SUCCESS");
				responseDTO.setOperationSucceeded(true);
				createHistory(device);

			}

		}
		return responseDTO;
	}

	public ResponseDTO update(ATEPairingDTO ateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Device device = deviceClient.show(ateDTO.getMacAddress());
		if (device == null) {
			responseDTO.setOperationSucceeded(false);
			responseDTO.setErrorCode(-100);
			responseDTO.setErrorDescription("Device not found");
		} else {
			if (!Integer.toString(ATEState.PROVISIONED.getValue()).equals(device.getStatus())) {
				responseDTO.setOperationSucceeded(false);
				responseDTO.setErrorCode(-102);
				responseDTO.setErrorDescription("Device is not in proper state");
			} else {
				Device dev = deviceClient.show(ateDTO.getAteTagNo());
				if (dev != null) {
					responseDTO.setErrorCode(-103);
					responseDTO.setErrorDescription("Device Already exist");
					responseDTO.setOperationSucceeded(false);
				} else {
					com.sap.iotservices.api.models.Device coreDevice = coreProxyClient.readDevice(device.getDeviceId());
					CustomProperty[] customProperties = coreDevice.getCustomProperties();
					customizeCustomProperties(customProperties, ATEState.PAIRED.getValue());
					Map<String, String> prop = new HashMap<String, String>();
					prop.put(APIConstant.ATE_TAG_NO, ateDTO.getAteTagNo());
					prop.put(APIConstant.EMIRATE, ateDTO.getEmirate());
					prop.put(APIConstant.ATE_AREA_CODE, ateDTO.getAreaCode());
					CustomProperty[] properties = addCustomProperties(customProperties, prop);
					coreDevice.setCustomProperties(properties);
					coreProxyClient.updateDevice(coreDevice, device.getDeviceId());
					device.setStatus(Integer.toString(ATEState.PAIRED.getValue()));
					device.setDeviceTagNo(ateDTO.getAteTagNo());
					deviceClient.update(device);
					responseDTO.setErrorCode(0);
					responseDTO.setErrorDescription("SUCCESS");
					responseDTO.setOperationSucceeded(true);
					createHistory(device);
				}
			}
		}
		return responseDTO;
	}

	public void createHistory(final Device device) {
		executorService.submit(new Runnable() {

			public void run() {
				ATEHistory ateHistory = new ATEHistory();
				// ateHistory.setAreaCode(device.get);
				ateHistory.setAteTagNo(device.getDeviceTagNo());
				ateHistory.setMacAddress(device.getMacAddress());
				ateHistory.setNumberOfInputs(device.getActualNumberOfInputs());
				ateHistory.setState(Integer.parseInt(device.getStatus()));
				ateHistory.setBuildingCls(Integer.parseInt(device.getDeviceClass()));
				ateHistoryRepo.save(ateHistory);
			}
		});
	}

	public void startProvisioning(final Device device) {
		executorService.submit(new Runnable() {
			public void run() {

				dpmInterface.provisioning(device);
			}
		});
	}

	public void cacheMessages(final List<AlarmMessageDTO> alarmMessageDTOs, final long deviceId) {
		executorService.submit(new Runnable() {
			public void run() {
				Gson gson = new Gson();
				String alarms = gson.toJson(alarmMessageDTOs);
				ProvisionTransactions provisionTransactions = new ProvisionTransactions();
				provisionTransactions.setAlarmPayload(alarms);
				provisionTransactions.setAteTagNo(alarmMessageDTOs.get(0).getAteTagNo());
				provisionTransactions.setState(0);
				provisionTransactions.setTransactionId(deviceId);
				provisionTransactionRepo.save(provisionTransactions);
			}
		});
	}
	public void cacheMessages(final MessageDTO messageDTO) {
		executorService.submit(new Runnable() {
			public void run() {
				Gson gson = new Gson();
				String alarms =null;
				if(messageDTO instanceof AlarmMessageDTO){
					AlarmMessageDTO alarmMessageDTO=(AlarmMessageDTO)messageDTO;
					alarms = gson.toJson(alarmMessageDTO);
				}else if(messageDTO instanceof HeartBeatDTO){
					HeartBeatDTO alarmMessageDTO=(HeartBeatDTO)messageDTO;
					alarms = gson.toJson(alarmMessageDTO);
				}
				
				ProvisionTransactions provisionTransactions = new ProvisionTransactions();
				provisionTransactions.setAlarmPayload(alarms);
				provisionTransactions.setAteTagNo(messageDTO.getAteTagNo());
				provisionTransactions.setState(0);				
				provisionTransactionRepo.save(provisionTransactions);
			}
		});
	}

	public void statusUpdate(final Device device) {
		executorService.submit(new Runnable() {
			public void run() {
				com.sap.iotservices.api.models.Device coreDevice = coreProxyClient.readDevice(device.getDeviceId());
				customizeCustomProperties(coreDevice.getCustomProperties(), Integer.parseInt(device.getStatus()));
				coreProxyClient.updateDevice(coreDevice, device.getDeviceId());
				deviceClient.update(device);
				createHistory(device);
			}
		});
	}

	public List<ATEHistory> getHistory() {
		return ateHistoryRepo.findAll();
	}

	public ResponseDTO confirmProvision(AckMessage ackMessage) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (ackMessage != null) {
			if (TransactionState.SUCCESS.name().equalsIgnoreCase(ackMessage.getTransactionStatus())) {
				changeState(ackMessage, ATEState.ACTIVE.getValue(), TransactionState.SUCCESS.getValue(), null);
			} else {
				changeState(ackMessage, ATEState.FAILEDACTIVATION.getValue(), TransactionState.FAILED.getValue(), null);
			}

		}

		return responseDTO;
	}

	private void changeState(AckMessage ackMessage, int state, int transactionState, Device caheDevice) {
		Device device = caheDevice;
		if (caheDevice == null)
			device = deviceClient.show(ackMessage.getAteTagNo());
		com.sap.iotservices.api.models.Device coreDevice = coreProxyClient.readDevice(device.getDeviceId());
		customizeCustomProperties(coreDevice.getCustomProperties(), state);
		device.setStatus(Integer.toString(state));
		deviceClient.update(device);
		ProvisionTransactions provisionTransactions = provisionTransactionRepo
				.findByTransactionId(ackMessage.getTransactionId());
		provisionTransactions.setState(transactionState);
		provisionTransactionRepo.save(provisionTransactions);
		if (state == ATEState.PREACRIVATED.getValue()) {
			Gson gson = new Gson();
			java.lang.reflect.Type alarmsListType = new TypeToken<ArrayList<AlarmMessageDTO>>() {
			}.getType();
			List<MessageDTO> alarmsList = gson.fromJson(provisionTransactions.getAlarmPayload(), alarmsListType);
			alarmsList.add(0, constructDeviceProvisionMessage(ackMessage.getAteTagNo(),
					device.getActualNumberOfInputs(), ackMessage.getTransactionId()));
			
			vidsysProxyClient.provisionActions(alarmsList);
		}
		createHistory(device);
	}

	private MessageDTO constructDeviceProvisionMessage(String tagNum, int numberOfInput, long transactionId) {
		ProvisionMessageDTO messageDTO = new ProvisionMessageDTO();
		messageDTO.setMessageType("PROVISIONING");
		messageDTO.setAteTagNo(tagNum);
		messageDTO.setNumberOfInput(numberOfInput);
		messageDTO.setTransactionId(transactionId);
		return messageDTO;
	}

}
