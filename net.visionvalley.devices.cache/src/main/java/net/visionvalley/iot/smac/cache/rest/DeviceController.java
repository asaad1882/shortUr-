package net.visionvalley.iot.smac.cache.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import net.visionvalley.iot.smac.cache.domain.Device;

import net.visionvalley.iot.smac.cache.service.DeviceService;


import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(path = "/device", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    List<Device> deviceList() {
        return deviceService.findAll();
    }

    @RequestMapping(value = "/device/{macAddress}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Device show(@PathVariable("macAddress") String id) {
        return deviceService.findById(id);
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Device update(@RequestBody Device device) {
        return deviceService.save(device);
    }

}
