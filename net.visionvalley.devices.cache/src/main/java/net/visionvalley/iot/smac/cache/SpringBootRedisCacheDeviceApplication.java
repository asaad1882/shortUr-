package net.visionvalley.iot.smac.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class SpringBootRedisCacheDeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisCacheDeviceApplication.class, args);
	}
}
