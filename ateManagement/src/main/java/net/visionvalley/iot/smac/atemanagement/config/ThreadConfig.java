package net.visionvalley.iot.smac.atemanagement.config;

import java.util.concurrent.ExecutorService;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executors;





@Configuration
@EnableAsync
public class ThreadConfig {

	@Bean
	@Primary
    public ExecutorService threadPoolTaskExecutor() {
		 return  Executors.newFixedThreadPool(5);      
       

    }

}
