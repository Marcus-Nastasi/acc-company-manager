package com.accenture.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *
 * The company manager api
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
@SpringBootApplication
@EnableCaching
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
