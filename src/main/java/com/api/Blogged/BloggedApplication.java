package com.api.Blogged;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BloggedApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggedApplication.class, args);
	}

}
