package com.victorlh.registrocontable.authservice.infrastructure.repositories;

import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	@Query("select r from RoleEntity r where r.nombre = ?1")
	RoleEntity findByNombre(String nombre);

}
