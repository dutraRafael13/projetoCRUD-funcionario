package com.ciss.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ciss.domain.Funcionario;
import com.ciss.dto.FuncionarioDTO;
import com.ciss.repositories.FuncionarioRepository;
import com.ciss.resources.exceptions.FieldMessage;

public class FuncionarioUpdateValidator implements ConstraintValidator<FuncionarioUpdate, FuncionarioDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private FuncionarioRepository repository;

	@Override
	public void initialize(FuncionarioUpdate ann) {
	}
	
	@Override
	public boolean isValid(FuncionarioDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> lista = this.validaEmailEPIS(repository.findByEmail(objDto.getEmail()),
				repository.findByNumeroPIS(objDto.getNumeroPIS()));
		for (FieldMessage e : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return lista.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	private List<FieldMessage> validaEmailEPIS(Funcionario funcionarioEmail, Funcionario funcionarioPIS) {
		List<FieldMessage> lista = new ArrayList<>();
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		int id = Integer.parseInt(map.get("id"));
		if (funcionarioEmail != null && id != funcionarioEmail.getId()) {
			lista.add(new FieldMessage("email", "E-mail já existente"));
		}
		if (funcionarioPIS != null && id != funcionarioPIS.getId()) {
			lista.add(new FieldMessage("numeroPIS", "Número PIS já existente"));
		}
		return lista;
	}
	
}