package net.visionvalley.iot.smac.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import net.visionvalley.iot.smac.cache.domain.Device;



@Configuration
public class RedisConfig{

    @Autowired
    private JedisConnectionFactory jedisConnFactory;

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public JacksonJsonRedisSerializer<Device> jacksonJsonRedisJsonSerializer() {
        JacksonJsonRedisSerializer<Device> jacksonJsonRedisJsonSerializer = new JacksonJsonRedisSerializer<Device>(Device.class);
        return jacksonJsonRedisJsonSerializer;
    }


    @Bean
    public RedisTemplate<String, Device> redisTemplate() {
        RedisTemplate<String, Device> redisTemplate = new RedisTemplate<String, Device>();
        redisTemplate.setConnectionFactory(jedisConnFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());
        return redisTemplate;
    }


}
