package com.ciss.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciss.domain.Funcionario;
import com.ciss.dto.FuncionarioDTO;
import com.ciss.dto.FuncionarioNewDTO;
import com.ciss.repositories.FuncionarioRepository;
import com.ciss.services.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {
	
	private FuncionarioRepository repository;
	
	@Autowired
	public FuncionarioService(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	public Funcionario insert(Funcionario funcionario) {
		funcionario.setId(null);
		return repository.save(funcionario);
	}
	
	public void delete(Integer id) {
		Optional<Funcionario> funcionario = repository.findById(id);
		if (!funcionario.isPresent()) {
			throw new ObjectNotFoundException("Funcionário não encontrado, ID: " + id);
		}
		repository.deleteById(funcionario.get().getId());
	}
	
	public Funcionario update(Funcionario funcionario) {
		return repository.saveAndFlush(funcionario);
	}
	
	public Funcionario find(Integer id) {
		Optional<Funcionario> funcionario = repository.findById(id);
		return funcionario.orElseThrow(() -> new ObjectNotFoundException("Funcionário não encontrado, ID: " + id));
	}
	
	public Funcionario fromDTO(FuncionarioDTO dto) {
		return new Funcionario(dto.getId(), dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getNumeroPIS());
	}
	
	public Funcionario fromNewDTO(FuncionarioNewDTO dto) {
		return new Funcionario(dto.getId(), dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getNumeroPIS());
	}

}