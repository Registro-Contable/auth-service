package com.victorlh.registrocontable.authservice.api.controllers;

import com.victorlh.registrocontable.authservice.api.dto.request.CrearUsuarioRequestDTO;
import com.victorlh.registrocontable.authservice.api.dto.response.UsuarioResponseDTO;
import com.victorlh.registrocontable.authservice.domain.services.UsuarioService;
import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.RoleEntity;
import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class AuthController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/crear")
	public UsuarioResponseDTO crearUsuario(@RequestBody CrearUsuarioRequestDTO crearUsuarioRequestDTO) {
		// TODO - Validaciones

		String uid = UUID.randomUUID().toString().replace("-", "");

		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setUid(uid);
		usuarioEntity.setEmail(crearUsuarioRequestDTO.getEmail());
		usuarioEntity.setNombre(crearUsuarioRequestDTO.getNombre());
		usuarioEntity.setApellidos(crearUsuarioRequestDTO.getApellidos());
		usuarioEntity.setEnabled(true);
		usuarioEntity.setPassword(passwordEncoder.encode(crearUsuarioRequestDTO.getPassword()));

		Optional<RoleEntity> roleOpt = usuarioService.findRoleByName("ROLE_USER");
		RoleEntity roleEntity = roleOpt.orElse(null);
		if (roleEntity != null) {
			usuarioEntity.setRoles(Arrays.asList(roleEntity));
		}

		usuarioService.persist(usuarioEntity);

		return new UsuarioResponseDTO(usuarioEntity);

	}
}
