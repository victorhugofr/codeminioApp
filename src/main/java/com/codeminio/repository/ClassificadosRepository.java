package com.codeminio.repository;

import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Classificados;

public interface ClassificadosRepository extends GenericRepository<Classificados>{
	
	List<Classificados> findAll();

	Optional<Classificados> findById(Long id);

}
