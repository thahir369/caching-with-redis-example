package com.tthahir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingWithRedisExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingWithRedisExampleApplication.class, args);
	}

}
