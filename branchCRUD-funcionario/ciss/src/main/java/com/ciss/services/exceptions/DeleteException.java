package com.ciss.services.exceptions;

public class DeleteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DeleteException(String mensagem) {
		super(mensagem);
	}
	
	public DeleteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}