package com.victorlh.registrocontable.authservice.infrastructure.entities.usuarios;

import com.victorlh.registrocontable.authservice.infrastructure.entities.enums.StatusDB;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role", length = 50)
	private String role;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusDB status;

}
