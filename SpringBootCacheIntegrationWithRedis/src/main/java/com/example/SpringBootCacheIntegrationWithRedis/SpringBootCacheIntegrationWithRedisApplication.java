package com.example.SpringBootCacheIntegrationWithRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootCacheIntegrationWithRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheIntegrationWithRedisApplication.class, args);
	}

}
