package com.victorlh.registrocontable.authservice.models.rest;

import com.victorlh.registrocontable.authservice.models.entities.usuarios.Usuario;

public class UsuarioResponse {

	private String uid;
	private String email;
	private String nombre;
	private String apellidos;
	private boolean isActivo;

	public UsuarioResponse() {
	}

	public UsuarioResponse(Usuario usuario) {
		this.uid = usuario.getUid();
		this.email = usuario.getEmail();
		this.nombre = usuario.getNombre();
		this.apellidos = usuario.getApellidos();
		this.isActivo = usuario.getEnabled();
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
