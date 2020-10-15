package com.codeminio.service;

import java.util.List;
import com.codeminio.dominio.Visita;

public interface VisitaService {
	
	List<Visita> index();

	void store(String username, Visita visita);

}
