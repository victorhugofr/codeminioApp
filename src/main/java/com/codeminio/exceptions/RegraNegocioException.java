package com.codeminio.exceptions;

import com.codeminio.dominio.ListaMensagens;

public class RegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ListaMensagens errors;

	public RegraNegocioException(String msg) {
		super(msg);
	}
	
	public RegraNegocioException(ListaMensagens errors) {
		this.errors = errors;
	}
}
