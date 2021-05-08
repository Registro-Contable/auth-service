package com.victorlh.registrocontable.authservice.domain.services.impl;

import com.victorlh.registrocontable.authservice.infrastructure.configuration.Oauth2Configuration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service("ApiClientServiceImpl")
public class ApiClientServiceImpl implements ClientDetailsService {

	private static final String[] AUTHORIZED_GRANT_TYPES = {"password", "client_credentials"};

	private final Oauth2Configuration configuration;

	@Autowired
	public ApiClientServiceImpl(Oauth2Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		if (!StringUtils.equals(clientId, this.configuration.getClientId())) {
			return null;
		}

		BaseClientDetails baseClientDetails = new BaseClientDetails();
		baseClientDetails.setClientId(clientId);
		baseClientDetails.setClientSecret(this.configuration.getClientSecret());
		baseClientDetails.setScope(this.configuration.getScopes());
		baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(AUTHORIZED_GRANT_TYPES));
		baseClientDetails.setAccessTokenValiditySeconds(this.configuration.getAccessTokenValidity());
		baseClientDetails.setRefreshTokenValiditySeconds(this.configuration.getRefreshTokenValidity());
		baseClientDetails.setRegisteredRedirectUri(Set.of("http://localhost:8081/oauth/login/client-app", "https://oauth.pstmn.io/v1/callback"));

		return baseClientDetails;
	}

}
