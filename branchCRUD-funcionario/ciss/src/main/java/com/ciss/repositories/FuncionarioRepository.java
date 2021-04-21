package com.ciss.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ciss.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	
	@Transactional(readOnly = true)
	Funcionario findByEmail(String email);
	
	@Transactional(readOnly = true)
	Funcionario findByNumeroPis(Long numeroPis);

}