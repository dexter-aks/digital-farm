package com.dexter.labs.digitalfarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.dexter.labs.digitalfarm.entity"})
public class DigitalFarmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalFarmServiceApplication.class, args);
	}

}
