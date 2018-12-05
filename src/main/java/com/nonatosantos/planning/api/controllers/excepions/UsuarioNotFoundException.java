package com.nonatosantos.planning.api.controllers.excepions;

public class UsuarioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException(String exception) {
		super(exception);
	}
}
