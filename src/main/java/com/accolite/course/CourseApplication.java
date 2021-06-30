package com.accolite.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication

public class CourseApplication {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
