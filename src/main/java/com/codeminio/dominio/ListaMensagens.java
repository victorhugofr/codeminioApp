package com.codeminio.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.codeminio.enums.CodigoMensagem;
import com.codeminio.exceptions.RegraNegocioException;


public class ListaMensagens implements Serializable {

	private List<CodigoMensagem> dangers = new ArrayList<>(); 
	private List<CodigoMensagem> warnings = new ArrayList<>(); 
	private List<CodigoMensagem> infos = new ArrayList<>(); 
	private List<CodigoMensagem> success = new ArrayList<>();
	
	public void adicionarErro(CodigoMensagem codigo) {
		dangers.add(codigo);
	}
	
	public void adicionarWarning(CodigoMensagem codigo) {
		warnings.add(codigo);
	}
	
	public void adicionarInfo(CodigoMensagem codigo) {
		infos.add(codigo);
	}
	
	public void adicionarSucesso(CodigoMensagem codigo) {
		success.add(codigo);
	}
	
	public List<CodigoMensagem> getErros() {
		return Collections.unmodifiableList(dangers);
	}
	
	public List<CodigoMensagem> getWarnings() {
		return Collections.unmodifiableList(warnings);
	}
	
	public List<CodigoMensagem> getInfos() {
		return Collections.unmodifiableList(infos);
	}
	
	public List<CodigoMensagem> getSuccess() {
		return Collections.unmodifiableList(success);
	}

	public void lancaExcecao() {
		if (dangers.isEmpty()) {
			return;
		}
		throw new RegraNegocioException(this);
	}
}
