package com.victorlh.registrocontable.authservice.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "security.oauth2.client")
public class Oauth2Configuration {

	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	private String clientId;
	private String clientSecret;
	private List<String> scopes;
}