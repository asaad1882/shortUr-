package net.visionvalley.iot.smac.atemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())  
          .select()                                  
        
          .apis(RequestHandlerSelectors.basePackage("net.visionvalley.iot.smac.atemanagement"))
          .paths(PathSelectors.any())    
          .build();                                           
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "ATE Lifecycle management API", 
          "ATE has multiple states during its life such as Provisioning", 
          "API V0.1", 
          "Terms of service", 
          new Contact("Asmaa Saad", "http://visionvalley.net", "asmaa.saad@visionvalley.net"), 
          "License of API", "API license URL");
   }
}
