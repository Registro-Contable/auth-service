package com.victorlh.registrocontable.authservice.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.victorlh.registrocontable.authservice.models.entities.apiclients.ApiClient;

public interface ApiClientDao extends JpaRepository<ApiClient, Long> {

	@Query("select u from ApiClient u where u.clientId = ?1")
	public ApiClient findByClientId(String clientId);
}
