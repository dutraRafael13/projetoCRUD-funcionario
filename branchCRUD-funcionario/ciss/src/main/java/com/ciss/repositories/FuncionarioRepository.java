package com.ciss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ciss.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}