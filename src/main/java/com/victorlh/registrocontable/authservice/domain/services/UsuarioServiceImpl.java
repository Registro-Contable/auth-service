package com.victorlh.registrocontable.authservice.domain.services;

import com.victorlh.registrocontable.authservice.domain.model.UserDetailsImpl;
import com.victorlh.registrocontable.commons.users.domain.model.enums.Status;
import com.victorlh.registrocontable.commons.users.domain.model.users.User;
import com.victorlh.registrocontable.commons.users.domain.services.users.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	private final UsersService usersService;

	public UsuarioServiceImpl(UsersService usersService) {
		this.usersService = usersService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOptional = usersService.findUser(email);
		User user = userOptional
				.filter(u -> u.getStatus() == Status.ACTIVATED || u.getStatus() == Status.DISABLED)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new UserDetailsImpl(user);
	}
}
