package com.victorlh.registrocontable.authservice.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.victorlh.registrocontable.authservice.models.entities.usuarios.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

	@Query("select r from Role r where r.nombre = ?1")
	public Role findByNombre(String nombre);

}
