package com.oracle.oBootBoard03.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	   @Value("${com.oracle.oBootBoard03.upload.path}")
	    private String uploadPath;
	    
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/upload/**")
	                .addResourceLocations("file:///" + System.getProperty("user.dir") + "/" + uploadPath + "/");
	    }
}
