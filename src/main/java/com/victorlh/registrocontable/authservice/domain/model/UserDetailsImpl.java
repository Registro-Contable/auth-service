package com.victorlh.registrocontable.authservice.domain.model;

import com.victorlh.registrocontable.commons.users.domain.model.enums.Status;
import com.victorlh.registrocontable.commons.users.domain.model.users.User;
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

	private final User user;
	private final List<GrantedAuthority> authorities;

	public UserDetailsImpl(User user) {
		this.user = user;
		this.authorities = user.getRoles().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
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
		return user.getStatus() == Status.ACTIVATED;
	}

	public String getNombre() {
		return user.getNombre();
	}

	public String getApellidos() {
		return user.getApellidos();
	}

	public UUID getUUID() {
		return user.getUuid();
	}

}
