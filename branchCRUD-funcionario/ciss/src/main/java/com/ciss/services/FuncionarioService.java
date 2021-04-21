package com.ciss.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciss.domain.Funcionario;
import com.ciss.repositories.FuncionarioRepository;
import com.ciss.services.exceptions.DeleteException;
import com.ciss.services.exceptions.ObjectNotFoundException;
import com.ciss.services.exceptions.ObjectNullException;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repository;
	
	public Funcionario insert(Funcionario funcionario) {
		if (funcionario != null) {
			funcionario.setId(null);
			return repository.save(funcionario);
		}
		throw new ObjectNullException("Não é possível inserir um objeto nulo");
	}
	
	public void delete(Integer id) {
		try {
			Optional<Funcionario> funcionario = repository.findById(id);
			if (funcionario.isPresent()) {
				repository.deleteById(funcionario.get().getId());
			} else {
				throw new ObjectNotFoundException("Funcionário não encontrado, ID: " + id);
			}
		} catch (Exception e) {
			throw new DeleteException("Não foi possível excluir funcionário");
		}
	}
	
	public Funcionario update(Funcionario funcionario) {
		if (funcionario != null) {
			return repository.saveAndFlush(funcionario);
		}
		throw new ObjectNullException("Não é possível atualizar um objeto nulo");
	}
	
	public Funcionario find(Integer id) {
		Optional<Funcionario> funcionario = repository.findById(id);
		return funcionario.orElseThrow(() -> new ObjectNotFoundException("Funcionário não encontrado, ID: " + id));
	}

}