package com.victorlh.registrocontable.authservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.victorlh.registrocontable.authservice.models.UserDetailsImpl;
import com.victorlh.registrocontable.authservice.models.dao.RoleDao;
import com.victorlh.registrocontable.authservice.models.dao.UsuarioDao;
import com.victorlh.registrocontable.authservice.models.entities.usuarios.Role;
import com.victorlh.registrocontable.authservice.models.entities.usuarios.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	public Optional<Usuario> findById(long id) {
		return usuarioDao.findById(id);
	}

	@Override
	public Optional<Usuario> findByUid(String uid) {
		Usuario usuario = usuarioDao.findByUid(uid);
		return Optional.ofNullable(usuario);
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		Usuario usuario = usuarioDao.findByEmail(email);
		return Optional.ofNullable(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOpt = findByEmail(email);
		Usuario usuario = usuarioOpt.orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));
		return new UserDetailsImpl(usuario);
	}

	@Override
	public void persist(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	public Optional<Role> findRoleByName(String name) {
		Role role = roleDao.findByNombre(name);
		return Optional.ofNullable(role);
	}

}
