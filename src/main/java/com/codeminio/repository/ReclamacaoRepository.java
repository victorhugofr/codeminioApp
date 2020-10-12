package com.codeminio.repository;

import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Reclamacao;

public interface ReclamacaoRepository extends GenericRepository<Reclamacao>{

	List<Reclamacao> findAll();

	Optional<Reclamacao> findById(Long id);
	
}
