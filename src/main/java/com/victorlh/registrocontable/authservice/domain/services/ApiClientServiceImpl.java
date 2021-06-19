package com.victorlh.registrocontable.authservice.domain.services;

import com.victorlh.registrocontable.commons.users.domain.model.apiclients.ApiClient;
import com.victorlh.registrocontable.commons.users.domain.model.enums.Status;
import com.victorlh.registrocontable.commons.users.domain.services.apiclients.ApiClientsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("ApiClientServiceImpl")
public class ApiClientServiceImpl implements ClientDetailsService {

	private static final String[] AUTHORIZED_GRANT_TYPES = {"password", "client_credentials"};
	private static final int DEFAULT_ACCESS_TOKEN_VALIDITY = 3600;
	private static final int DEFAULT_REFRESH_TOKEN_VALIDITY = 7200;

	private final ApiClientsService apiClientsService;

	@Autowired
	public ApiClientServiceImpl(ApiClientsService apiClientsService) {
		this.apiClientsService = apiClientsService;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Optional<ApiClient> apiClientOptional = apiClientsService.findClient(clientId);
		return apiClientOptional
				.filter(c -> c.getStatus() == Status.ACTIVATED)
				.map(ApiClientServiceImpl::convert)
				.orElse(null);
	}

	private static BaseClientDetails convert(ApiClient apiClient) {
		List<String> scopes = apiClient.getScopes();
		Integer accessTokenValidity = apiClient.getAccessTokenValidity();
		Integer refreshTokenValidity = apiClient.getRefreshTokenValidity();

		BaseClientDetails baseClientDetails = new BaseClientDetails();
		baseClientDetails.setClientId(apiClient.getClientId());
		baseClientDetails.setClientSecret(apiClient.getClientSecret());
		baseClientDetails.setScope(scopes);
		baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(AUTHORIZED_GRANT_TYPES));
		baseClientDetails.setAccessTokenValiditySeconds(accessTokenValidity == null ? DEFAULT_ACCESS_TOKEN_VALIDITY : accessTokenValidity);
		baseClientDetails.setRefreshTokenValiditySeconds(refreshTokenValidity == null ? DEFAULT_REFRESH_TOKEN_VALIDITY : refreshTokenValidity);
		return baseClientDetails;
	}

}
