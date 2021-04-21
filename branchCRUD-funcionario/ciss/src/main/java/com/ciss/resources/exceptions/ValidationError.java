package com.ciss.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros;

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		this.erros = new ArrayList<>();
	}

	public List<FieldMessage> getErrors() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		erros.add(new FieldMessage(fieldName, message));
	}

}