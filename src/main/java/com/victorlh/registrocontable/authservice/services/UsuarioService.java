package com.victorlh.registrocontable.authservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.victorlh.registrocontable.authservice.models.entities.usuarios.Role;
import com.victorlh.registrocontable.authservice.models.entities.usuarios.Usuario;

public interface UsuarioService extends UserDetailsService {

	List<Usuario> findAll();

	Optional<Usuario> findById(long id);

	Optional<Usuario> findByUid(String uid);

	Optional<Usuario> findByEmail(String email);

	void persist(Usuario usuario);

	Optional<Role> findRoleByName(String name);
}
