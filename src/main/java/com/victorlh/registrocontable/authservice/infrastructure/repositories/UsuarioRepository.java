package com.victorlh.registrocontable.authservice.infrastructure.repositories;

import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	@Query("From UsuarioEntity u where u.uuid = :uuid and u.status = 'ACTIVATED' order by id desc")
	Optional<UsuarioEntity> findByUUID(UUID uuid);

	@Query("select u from UsuarioEntity u where u.email = :email and u.status = 'ACTIVATED' order by id desc")
	Optional<UsuarioEntity> findByEmail(String email);

}
