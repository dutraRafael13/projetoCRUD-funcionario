package com.ciss.services.exceptions;

public class ObjectNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNullException(String mensagem) {
		super(mensagem);
	}
	
	public ObjectNullException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}