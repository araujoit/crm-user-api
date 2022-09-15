package br.com.araujoit.crmuserapi.entities;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrmUserDto implements Serializable {

	private static final long serialVersionUID = 2336985907023709032L;

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
	private transient String password;
}
