package com.victorlh.registrocontable.authservice.infrastructure.repositories;

import com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	@Query("select u from UsuarioEntity u where u.uid = ?1")
	UsuarioEntity findByUid(String username);

	@Query("select u from UsuarioEntity u where u.email = ?1")
	UsuarioEntity findByEmail(String email);

}
