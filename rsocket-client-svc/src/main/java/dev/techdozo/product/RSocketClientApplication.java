package dev.techdozo.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RSocketClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RSocketClientApplication.class, args);
	}

}
