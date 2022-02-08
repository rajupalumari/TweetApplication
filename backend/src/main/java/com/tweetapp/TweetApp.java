package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;


@SpringBootApplication
@OpenAPIDefinition
public class TweetApp {

	public static void main(String[] args) {
		SpringApplication.run(TweetApp.class, args);
	}
	
}
