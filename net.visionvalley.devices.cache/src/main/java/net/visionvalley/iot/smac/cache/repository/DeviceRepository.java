package net.visionvalley.iot.smac.cache.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import net.visionvalley.iot.smac.cache.domain.Device;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class DeviceRepository {

    @Autowired
    private RedisTemplate<String, Device> redisTemplate;

    public void save(Device device) {
        redisTemplate.opsForValue().set(device.getMacAddress(), device);
        redisTemplate.expire( device.getMacAddress(), -11, TimeUnit.DAYS );
       
    }

    public Device findById(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public List<Device> findAll() {
        List<Device> userList = new ArrayList<Device>();

        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            userList.add(findById(it.next()));
        }

        return userList;
    }

    public void delete(Device device) {
        String key = device.getMacAddress();
        redisTemplate.opsForValue().getOperations().delete(key);
    }

}
