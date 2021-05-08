package com.victorlh.registrocontable.authservice.domain.services;

import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.RoleEntity;
import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService extends UserDetailsService {

	List<UsuarioEntity> findAll();

	Optional<UsuarioEntity> findById(long id);

	Optional<UsuarioEntity> findByUUID(UUID uuid);

	Optional<UsuarioEntity> findByEmail(String email);

	void persist(UsuarioEntity usuarioEntity);

	Optional<RoleEntity> findRoleByName(String role);
}
