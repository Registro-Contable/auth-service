package com.victorlh.registrocontable.authservice.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.victorlh.registrocontable.authservice.models.entities.usuarios.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where u.uid = ?1")
	public Usuario findByUid(String username);

	@Query("select u from Usuario u where u.email = ?1")
	public Usuario findByEmail(String email);

}
