package com.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.crud.space")
@SpringBootApplication
public class Crud2Application {

	public static void main(String[] args) {
		SpringApplication.run(Crud2Application.class, args);
	}

}
