package com.victorlh.registrocontable.authservice.api.dto.response;

import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.UsuarioEntity;

public class UsuarioResponseDTO {

	private String uid;
	private String email;
	private String nombre;
	private String apellidos;
	private boolean isActivo;

	public UsuarioResponseDTO() {
	}

	public UsuarioResponseDTO(UsuarioEntity usuarioEntity) {
		this.uid = usuarioEntity.getUid();
		this.email = usuarioEntity.getEmail();
		this.nombre = usuarioEntity.getNombre();
		this.apellidos = usuarioEntity.getApellidos();
		this.isActivo = usuarioEntity.getEnabled();
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

}
