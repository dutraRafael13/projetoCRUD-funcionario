package com.ciss.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ciss.domain.Funcionario;
import com.ciss.dto.FuncionarioDTO;
import com.ciss.repositories.FuncionarioRepository;
import com.ciss.resources.exceptions.FieldMessage;

public class FuncionarioInsertValidator implements ConstraintValidator<FuncionarioInsert, FuncionarioDTO> {
	
	@Autowired
	private FuncionarioRepository repository;

	@Override
	public void initialize(FuncionarioInsert ann) {
	}
	
	@Override
	public boolean isValid(FuncionarioDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> lista = this.validaEmailEPIS(repository.findByEmail(objDto.getEmail()),
				repository.findByNumeroPis(objDto.getNumeroPIS()));
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
			lista.add(new FieldMessage("E-mail", "E-mail já existente"));
		}
		if (funcionarioPIS != null) {
			lista.add(new FieldMessage("Número PIS", "Número PIS já existente"));
		}
		return lista;
	}
	
}