package com.AppRH.AppRH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppRhApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("kleber"));
	}

}
