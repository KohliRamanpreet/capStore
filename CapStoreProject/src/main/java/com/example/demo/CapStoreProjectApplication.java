package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories("com.capstore.repository")
@EntityScan("com.capstore.model")
@SpringBootApplication(scanBasePackages = {"com.capstore.controller","com.capstore,service"})
public class CapStoreProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapStoreProjectApplication.class, args);
	}

}
