package com.cravemate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cravemate")
@EntityScan("com.cravemate.pojos") 
@ComponentScan("com.cravemate")
@EnableJpaRepositories("com.cravemate.dao")
public class CravemateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CravemateApplication.class, args);
	}

}
