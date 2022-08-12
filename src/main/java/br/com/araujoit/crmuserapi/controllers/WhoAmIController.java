package br.com.araujoit.crmuserapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/whoami")
public class WhoAmIController {
	
	@Value("${user.role}")
	private String role;
	
	@GetMapping(value = "{username}", produces = "text/plain")
	public String whoami(@PathVariable("username") String username) {
		return String.format("Hello! Você é %s e se tornará %s...\n", username, role);
	}
}
