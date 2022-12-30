package com.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;

@RefreshScope
@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
public class BatchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchApiApplication.class, args);
	}

}
