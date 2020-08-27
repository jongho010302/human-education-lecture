package com.myworkspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MyworkspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyworkspaceApplication.class, args);
	}

}
