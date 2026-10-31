package com.shashi.bookmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookmovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmovieApplication.class, args);
	}

}
