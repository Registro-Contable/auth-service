package com.victorlh.registrocontable.authservice.domain.services;

import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.RoleEntity;
import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UsuarioService extends UserDetailsService {

	List<UsuarioEntity> findAll();

	Optional<UsuarioEntity> findById(long id);

	Optional<UsuarioEntity> findByUid(String uid);

	Optional<UsuarioEntity> findByEmail(String email);

	void persist(UsuarioEntity usuarioEntity);

	Optional<RoleEntity> findRoleByName(String name);
}
