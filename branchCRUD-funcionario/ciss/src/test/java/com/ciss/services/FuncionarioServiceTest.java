package com.ciss.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ciss.domain.Funcionario;
import com.ciss.repositories.FuncionarioRepository;
import com.ciss.services.exceptions.ObjectNotFoundException;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FuncionarioServiceTest {

	private FuncionarioService service;
	Funcionario funcionario;

	@Mock
	private FuncionarioRepository repository;
	
	@BeforeEach
	public void inicia() {
		service = new FuncionarioService(repository);
		funcionario = new Funcionario(1, "Rafael", "Dutra", "rafaelldutra_13@hotmail.com", 12345678910L);
	}

	@Test
	public void testeInsert() {
		when(repository.save(funcionario)).thenReturn(funcionario);
		assertNotNull(service.insert(funcionario));
	}
	
	@Test
	public void testeDelete() {
		int id = 1;
		when(repository.findById(id)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.delete(id));
	}
	
	public void testeUpdate() {
		when(repository.saveAndFlush(funcionario)).thenReturn(funcionario);
		assertNotNull(service.update(funcionario));
	}
	
	public void testeFind() {
		int id = 1;
		when(repository.findById(id)).thenReturn(Optional.of(funcionario));
		assertNotNull(service.find(id));
	}
	
	public void testeFindException() {
		int id = 1;
		when(repository.findById(id)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.find(id));
	}

}