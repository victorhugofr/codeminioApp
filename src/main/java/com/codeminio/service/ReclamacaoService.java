package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Reclamacao;

public interface ReclamacaoService {
	
	List<Reclamacao> index();

	void store(String username, Reclamacao reclamacao);

}
