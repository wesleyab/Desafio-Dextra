package com.atomic.burguer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.atomic.burguer.property.AtomicApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(AtomicApiProperty.class)
public class AtomicBurguerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtomicBurguerApplication.class, args);
	}
}