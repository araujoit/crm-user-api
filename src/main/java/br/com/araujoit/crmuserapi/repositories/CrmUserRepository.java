package br.com.araujoit.crmuserapi.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.araujoit.crmuserapi.entities.CrmUser;

@Repository
public interface CrmUserRepository extends JpaRepository<CrmUser, Long> {

	Optional<CrmUser> findByUsername(String username);
	
	Page<CrmUser> findAll(Pageable pageable);
}
