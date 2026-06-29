package com.janak.dataplatform;

import com.janak.dataplatform.security.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class AsyncDataPlatformApplication {

	public static void main(String[] args) {

		SpringApplication.run(AsyncDataPlatformApplication.class, args);
		System.out.println("This is running");
	}

}
