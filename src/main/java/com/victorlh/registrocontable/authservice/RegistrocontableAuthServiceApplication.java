package com.victorlh.registrocontable.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@ComponentScan(basePackages = "com.victorlh.registrocontable")
@EnableJpaRepositories("com.victorlh.registrocontable")
@EntityScan("com.victorlh.registrocontable")
public class RegistrocontableAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrocontableAuthServiceApplication.class, args);
	}

}
