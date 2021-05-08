package com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios;

import com.victorlh.registrocontable.authservice.infrastructure.entities.enums.StatusDB;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid", nullable = false)
	private UUID uuid;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", length = 256, nullable = false)
	private String password;

	@Column(name = "given_name", length = 100)
	private String nombre;

	@Column(name = "family_name", length = 100)
	private String apellidos;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusDB status;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rel_users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> roles;

}
