package com.victorlh.registrocontable.authservice.infrastructure.repositories;

import com.victorlh.registrocontable.authservice.infrastructure.entities.apiclients.ApiClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApiClientRepository extends JpaRepository<ApiClientEntity, Long> {

	@Query("select u from ApiClientEntity u where u.clientId = ?1")
	ApiClientEntity findByClientId(String clientId);
}
