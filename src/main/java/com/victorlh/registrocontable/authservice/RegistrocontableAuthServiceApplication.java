package com.victorlh.registrocontable.authservice;

import com.victorlh.gcp.spring.libfirestore.EnableFirestore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@ComponentScan(basePackages = "com.victorlh.registrocontable")
@EntityScan("com.victorlh.registrocontable")
@EnableFirestore
public class RegistrocontableAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrocontableAuthServiceApplication.class, args);
	}

}
