package br.com.araujoit.crmuserapi.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.araujoit.crmuserapi.entities.CrmUser;
import br.com.araujoit.crmuserapi.entities.CrmUserDto;
import br.com.araujoit.crmuserapi.repositories.CrmUserRepository;

@Service
public class CrmUserService {

	@Autowired
	private CrmUserRepository userRepository;

	public Optional<CrmUserDto> findByUsername(String username) {
		return userRepository.findByUsername(username).map(this::build);
	}

	public CrmUserDto insertNewUser(CrmUserDto userDto) {
		if (findByUsername(userDto.getUsername()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existente");
		}

		CrmUser crmUser = userRepository.save(build(userDto));
		return build(crmUser);
	}
	
	private CrmUserDto build(CrmUser user) {
		return CrmUserDto.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.surName(user.getSurName())
				.lastName(user.getLastName())
				.username(user.getUsername())
				.password(user.getPassword())
				.build();
	}

	private CrmUser build(CrmUserDto userDto) {
		return CrmUser.builder()
				.id(userDto.getId())
				.firstName(userDto.getFirstName())
				.surName(userDto.getSurName())
				.lastName(userDto.getLastName())
				.username(userDto.getUsername())
				.password(userDto.getPassword())
				.build();
	}

	public Page<CrmUser> findAll(Integer page, Integer size, String sortBy, String sortOrder) {
		Sort sort = Sort.by(sortBy).ascending();
		if ("DESC".equalsIgnoreCase(sortOrder)) {
			sort = Sort.by(sortBy).descending();
		}

		return userRepository.findAll(PageRequest.of(page, size, sort));
	}

	public void updateUser(@Valid Long id, @Valid CrmUserDto userDto) {
		userRepository.findById(id).ifPresent(user -> {
			userDto.setId(id);
			CrmUser userUpdated = build(userDto);
			userRepository.save(userUpdated);
		});
	}
}
