package net.visionvalley.iot.smac.atemanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeingConfig {
	@Value("${coreAPI.username}")
	private String username;
	@Value("${coreAPI.password}")
	private String password;
	
	 @Bean
	    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
	         return new BasicAuthRequestInterceptor(username, password);
	    }

}
