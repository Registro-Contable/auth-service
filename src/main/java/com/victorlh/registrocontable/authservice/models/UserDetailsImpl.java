package com.victorlh.registrocontable.authservice.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.victorlh.registrocontable.authservice.models.entities.usuarios.Usuario;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 6944468940675665342L;

	private Usuario usuario;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(Usuario usuario) {
		this.usuario = usuario;

		this.authorities = usuario.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getNombre())).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return usuario.getEnabled();
	}

	public String getNombre() {
		return usuario.getNombre();
	}

	public String getApellidos() {
		return usuario.getApellidos();
	}

	public String getUid() {
		return usuario.getUid();
	}

}
