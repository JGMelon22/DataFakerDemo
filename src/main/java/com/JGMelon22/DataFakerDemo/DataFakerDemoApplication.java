package com.JGMelon22.DataFakerDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DataFakerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataFakerDemoApplication.class, args);
	}

}
