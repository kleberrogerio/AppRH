package com.AppRH.AppRH;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan("com.AppRH.AppRH")
public class AppRhApplication {

	public static void main(String[] args)throws SQLException {
		SpringApplication.run(AppRhApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("kleber"));
	}
}
