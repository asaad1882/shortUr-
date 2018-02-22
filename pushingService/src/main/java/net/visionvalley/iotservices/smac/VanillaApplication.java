package net.visionvalley.iotservices.smac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.visionvalley.iotservices.smac.dto.CustomUserDetails;
import net.visionvalley.iotservices.smac.entities.Client;
import net.visionvalley.iotservices.smac.entities.Role;
import net.visionvalley.iotservices.smac.entities.User;
import net.visionvalley.iotservices.smac.repositories.UserRepository;
import net.visionvalley.iotservices.smac.services.ClientService;
import net.visionvalley.iotservices.smac.services.UserService;

import java.util.Arrays;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {OAuth2AutoConfiguration.class})
@EnableDiscoveryClient
public class VanillaApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(VanillaApplication.class, args);
	}

	/**
	 * Password grants are switched on by injecting an Authentication Manager.
	 * Here, we setup the builder so that the User Details Service is the one we coded.
	 * @param builder
	 * @param repository
	 * @throws Exception
     */
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService service, ClientService clientService) throws Exception {		
		if (repository.count()==0){
			service.save(new User("vidsysUsr", "Vidsys@123", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
			Client client=new Client();
			client.setClientId("VidSysClient");
			client.setClientSecret("secret");
			client.setAccessTokenValidity("-1");
			client.setRefreshTokenValidity("-1");
			client.setAuthorizedGrantTypes("authorization_code,password,refresh_token,implicit");
			client.setScope("read,write,trust");
			client.setAuthorities("ROLE_CLIENT,ROLE_TRUSTED_CLIENT");
			clientService.save(client);
		}
		
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
		
		
	}

	/**
	 * We return an instance of our Custom User Details.
	 * @param repository
	 * @return
     */
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}

}
