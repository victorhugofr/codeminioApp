package com.codeminio.exceptions;

import java.util.List;

import com.codeminio.dominio.ListaMensagens;

public class RegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ListaMensagens errors;
	private List<String> errorList;

	public RegraNegocioException(String msg) {
		super(msg);
	}
	
	public RegraNegocioException(ListaMensagens errors) {
		this.errors = errors;
	}

	public RegraNegocioException(List<String> errorList) {
		this.errorList = errorList;
	}

	public List<String> getErrorList() {
		return errorList;
	}

}
