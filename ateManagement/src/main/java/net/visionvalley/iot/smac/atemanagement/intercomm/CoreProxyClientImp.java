package net.visionvalley.iot.smac.atemanagement.intercomm;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sap.iotservices.api.models.Device;
//@Component
public class CoreProxyClientImp  {
	 
	public Device createDevice(Device device) {
		RestTemplate restTemplate=new RestTemplate();
		 String postUrl="http://192.168.200.67:8161/iot/core/api/v1/devices";
		  HttpEntity<Object> request = new HttpEntity<Object>(device,getHeaders());
		  ResponseEntity<Device> response = restTemplate.exchange(postUrl, HttpMethod.POST, request, Device.class);
		return response.getBody();
	}

	public Device readDevice(String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Device updateDevice(Device device, String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 private static HttpHeaders getHeaders(){
	      String plainCredentials="duadmin:IoT3st@123";
	      String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
	       
	      HttpHeaders headers = new HttpHeaders();
	      headers.add("Authorization", "Basic " + base64Credentials);
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      return headers;
	  }

}
