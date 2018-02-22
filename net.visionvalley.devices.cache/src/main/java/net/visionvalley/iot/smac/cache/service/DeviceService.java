package net.visionvalley.iot.smac.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.visionvalley.iot.smac.cache.domain.Device;

import net.visionvalley.iot.smac.cache.repository.DeviceRepository;


import java.util.List;

@Component
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Device findById(String macAddress) {
        return deviceRepository.findById(macAddress);
    }

    public Device save(Device device) {
    	
        deviceRepository.save(device);
        return deviceRepository.findById(device.getMacAddress());
    }

    public void delete(Device device) {
    	deviceRepository.delete(device);
    }


}
