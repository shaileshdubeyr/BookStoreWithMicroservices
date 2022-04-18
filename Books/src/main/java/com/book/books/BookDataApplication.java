package com.book.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableAspectJAutoProxy
@Configuration
@ComponentScan("com.book.books")
public class BookDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookDataApplication.class, args);
	}

}
