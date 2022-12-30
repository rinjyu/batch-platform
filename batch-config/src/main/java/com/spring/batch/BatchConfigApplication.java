package com.spring.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BatchConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchConfigApplication.class, args);
	}

}
