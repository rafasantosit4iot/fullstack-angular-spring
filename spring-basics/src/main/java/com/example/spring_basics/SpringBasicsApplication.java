package com.example.spring_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class SpringBasicsApplication {

	@RequestMapping("/")
	public String home() {
		return "Ola mundo";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicsApplication.class, args);
	}

}
