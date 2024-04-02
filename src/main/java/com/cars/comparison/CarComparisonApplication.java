package com.cars.comparison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CarComparisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarComparisonApplication.class, args);
	}

}
