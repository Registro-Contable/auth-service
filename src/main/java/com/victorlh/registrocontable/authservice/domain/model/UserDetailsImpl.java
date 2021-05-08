package com.victorlh.registrocontable.authservice.domain.model;

import com.victorlh.registrocontable.authservice.infrastructure.entities.enums.StatusDB;
import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
	@Serial
	private static final long serialVersionUID = 6944468940675665342L;

	private final UsuarioEntity usuarioEntity;
	private final List<GrantedAuthority> authorities;

	public UserDetailsImpl(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
		this.authorities = usuarioEntity.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return usuarioEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return usuarioEntity.getEmail();
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
		return usuarioEntity.getStatus() == StatusDB.ACTIVATED;
	}

	public String getNombre() {
		return usuarioEntity.getNombre();
	}

	public String getApellidos() {
		return usuarioEntity.getApellidos();
	}

	public UUID getUUID() {
		return usuarioEntity.getUuid();
	}

}
