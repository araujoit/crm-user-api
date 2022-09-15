package br.com.araujoit.crmuserapi.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.araujoit.crmuserapi.entities.CrmUser;
import br.com.araujoit.crmuserapi.entities.CrmUserDto;
import br.com.araujoit.crmuserapi.services.CrmUserService;

@RestController
@RequestMapping(value = "/user")
public class CrmUserController {

	@Autowired
	private CrmUserService userService;

	@GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
	public String hello() {
		return "Hello!";
	}

	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CrmUserDto findByUsername(String username) {
		return userService.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public CrmUserDto insertNewUser(@Valid @RequestBody CrmUserDto userDto) {
		return userService.insertNewUser(userDto);
	}

	@GetMapping(value = "/list")
	public Page<CrmUser> findAll(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder) {
		return userService.findAll(page, size, sortBy, sortOrder);
	}

	@PatchMapping(value = "/{id}")
	public void updateUser(@Valid @Min(1) @PathVariable(name = "id") Long id, @Valid @RequestBody CrmUserDto userDto) {
		userService.updateUser(id, userDto);
	}
}
