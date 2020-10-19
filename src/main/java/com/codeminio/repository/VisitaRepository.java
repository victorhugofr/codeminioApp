package com.codeminio.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.codeminio.dominio.Visita;

@Repository
public interface VisitaRepository extends GenericRepository<Visita>{

	List<Visita> findAll();

	Optional<Visita> findById(Long id);

	Optional<Visita> findByCpf(String cpf);
	
}
