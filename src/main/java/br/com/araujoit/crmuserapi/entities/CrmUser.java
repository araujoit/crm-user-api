package br.com.araujoit.crmuserapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "crm_user")
public class CrmUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Primeiro nome é obrigatório")
	@Size(min = 3, max = 100)
	private String firstName;

	@Size(min = 0, max = 100)
	private String surName;

	@NotBlank(message = "Último nome é obrigatório")
	@Size(min = 3, max = 100)
	private String lastName;

	@NotBlank(message = "Nome de usuário é obrigatório")
	@Size(min = 3, max = 100)
	private String username;

	@NotBlank(message = "Senha é obrigatória")
	@Size(min = 3, max = 100)
	private String password;

	public CrmUser(String firstName, String surName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.surName = surName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
}
