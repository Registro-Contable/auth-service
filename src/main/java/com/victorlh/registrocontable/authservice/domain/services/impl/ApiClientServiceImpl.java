package com.victorlh.registrocontable.authservice.domain.services.impl;

import com.victorlh.registrocontable.authservice.infrastructure.entities.apiclients.ApiClientEntity;
import com.victorlh.registrocontable.authservice.infrastructure.entities.apiclients.ApiClientsScopesEntity;
import com.victorlh.registrocontable.authservice.infrastructure.repositories.ApiClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("ApiClientServiceImpl")
public class ApiClientServiceImpl implements ClientDetailsService {

	private static final String[] AUTHORIZED_GRANT_TYPES = {"password", "refresh_token"};

	private final ApiClientRepository apiClientRepository;

	@Value("${registrocontable.auth.accessTokenValidity}")
	private Integer accessTokenValidity;
	@Value("${registrocontable.auth.refreshTokenValidity}")
	private Integer refreshTokenValidity;

	@Autowired
	public ApiClientServiceImpl(ApiClientRepository apiClientRepository) {
		this.apiClientRepository = apiClientRepository;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		ApiClientEntity apiClientEntity = apiClientRepository.findByClientId(clientId);
		List<String> scopes = apiClientEntity.getScopes().stream().map(ApiClientsScopesEntity::getScope).collect(Collectors.toList());

		BaseClientDetails baseClientDetails = new BaseClientDetails();
		baseClientDetails.setClientId(clientId);
		baseClientDetails.setClientSecret(apiClientEntity.getClientSecret());
		baseClientDetails.setScope(scopes);
		baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(AUTHORIZED_GRANT_TYPES));
		baseClientDetails.setAccessTokenValiditySeconds(accessTokenValidity);
		baseClientDetails.setRefreshTokenValiditySeconds(refreshTokenValidity);

		return baseClientDetails;
	}

}
