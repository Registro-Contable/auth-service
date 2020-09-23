package com.victorlh.registrocontable.authservice.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.victorlh.registrocontable.authservice.models.dao.ApiClientDao;
import com.victorlh.registrocontable.authservice.models.entities.apiclients.ApiClient;

@Service("ApiClientServiceImpl")
public class ApiClientServiceImpl implements ClientDetailsService {

	@Autowired
	private ApiClientDao apiClientDao;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		ApiClient apiClient = apiClientDao.findByClientId(clientId);
		List<String> scopes = apiClient.getScopes().stream().map(s -> s.getScope()).collect(Collectors.toList());

		BaseClientDetails baseClientDetails = new BaseClientDetails();
		baseClientDetails.setClientId(clientId);
		baseClientDetails.setClientSecret(apiClient.getClientSecret());
		baseClientDetails.setScope(scopes);
		baseClientDetails.setAuthorizedGrantTypes(Arrays.asList("password", "refresh_token"));
		baseClientDetails.setAccessTokenValiditySeconds(3600);
		baseClientDetails.setRefreshTokenValiditySeconds(3600);

		return baseClientDetails;
	}

}
