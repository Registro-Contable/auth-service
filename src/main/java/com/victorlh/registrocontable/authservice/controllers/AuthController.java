package com.victorlh.registrocontable.authservice.controllers;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorlh.registrocontable.authservice.models.entities.usuarios.Role;
import com.victorlh.registrocontable.authservice.models.entities.usuarios.Usuario;
import com.victorlh.registrocontable.authservice.models.rest.CrearUsuarioRequest;
import com.victorlh.registrocontable.authservice.models.rest.UsuarioResponse;
import com.victorlh.registrocontable.authservice.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class AuthController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/crear")
	public UsuarioResponse crearUsuario(@RequestBody CrearUsuarioRequest crearUsuarioRequest) {
		// TODO - Validaciones

		String uid = UUID.randomUUID().toString().replace("-", "");

		Usuario usuario = new Usuario();
		usuario.setUid(uid);
		usuario.setEmail(crearUsuarioRequest.getEmail());
		usuario.setNombre(crearUsuarioRequest.getNombre());
		usuario.setApellidos(crearUsuarioRequest.getApellidos());
		usuario.setEnabled(true);
		usuario.setPassword(passwordEncoder.encode(crearUsuarioRequest.getPassword()));

		Optional<Role> roleOpt = usuarioService.findRoleByName("ROLE_USER");
		Role role = roleOpt.orElse(null);
		if (role != null) {
			usuario.setRoles(Arrays.asList(role));
		}

		usuarioService.persist(usuario);

		return new UsuarioResponse(usuario);

	}
}
