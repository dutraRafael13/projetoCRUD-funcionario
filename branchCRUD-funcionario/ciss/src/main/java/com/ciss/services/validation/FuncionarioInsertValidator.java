package com.ciss.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ciss.domain.Funcionario;
import com.ciss.dto.FuncionarioDTO;
import com.ciss.dto.FuncionarioNewDTO;
import com.ciss.repositories.FuncionarioRepository;
import com.ciss.resources.exceptions.FieldMessage;

public class FuncionarioInsertValidator implements ConstraintValidator<FuncionarioInsert, FuncionarioNewDTO> {
	
	@Autowired
	private FuncionarioRepository repository;

	@Override
	public void initialize(FuncionarioInsert ann) {
	}
	
	@Override
	public boolean isValid(FuncionarioNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> lista = this.validaEmailEPIS(repository.findByEmail(objDto.getEmail()),
				repository.findByNumeroPIS(objDto.getNumeroPIS()));
		for (FieldMessage e : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return lista.isEmpty();
	}
	
	private List<FieldMessage> validaEmailEPIS(Funcionario funcionarioEmail, Funcionario funcionarioPIS) {
		List<FieldMessage> lista = new ArrayList<>();
		if (funcionarioEmail != null) {
			lista.add(new FieldMessage("email", "E-mail já existente"));
		}
		if (funcionarioPIS != null) {
			lista.add(new FieldMessage("numeroPIS", "Número PIS já existente"));
		}
		return lista;
	}
	
}