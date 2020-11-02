package com.codeminio.service;

import java.util.List;
import com.codeminio.dominio.Classificados;

public interface ClassificadosService {
	
	List<Classificados> index();

	void store(String username, Classificados classificados);

}
