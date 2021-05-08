package com.victorlh.registrocontable.authservice.infrastructure.repositories;

import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	@Query("From RoleEntity r where r.role = :role and r.status = 'ACTIVATED' order by id desc")
	Optional<RoleEntity> findByRole(String role);

}
