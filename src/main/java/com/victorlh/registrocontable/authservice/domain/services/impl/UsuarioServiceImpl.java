package com.victorlh.registrocontable.authservice.domain.services.impl;

import com.victorlh.registrocontable.authservice.domain.model.UserDetailsImpl;
import com.victorlh.registrocontable.authservice.domain.services.UsuarioService;
import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.RoleEntity;
import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.UsuarioEntity;
import com.victorlh.registrocontable.authservice.infrastructure.repositories.RoleRepository;
import com.victorlh.registrocontable.authservice.infrastructure.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final RoleRepository roleRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
		this.usuarioRepository = usuarioRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public List<UsuarioEntity> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<UsuarioEntity> findById(long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Optional<UsuarioEntity> findByUUID(UUID uuid) {
		return usuarioRepository.findByUUID(uuid);
	}

	@Override
	public Optional<UsuarioEntity> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UsuarioEntity> usuarioOpt = findByEmail(email);
		UsuarioEntity usuarioEntity = usuarioOpt.orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));
		return new UserDetailsImpl(usuarioEntity);
	}

	@Override
	public void persist(UsuarioEntity usuarioEntity) {
		usuarioRepository.save(usuarioEntity);
	}

	@Override
	public Optional<RoleEntity> findRoleByName(String role) {
		return roleRepository.findByRole(role);
	}

}
