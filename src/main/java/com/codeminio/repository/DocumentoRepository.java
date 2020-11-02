package com.codeminio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codeminio.dominio.Documento;

@Repository
@Transactional
public interface DocumentoRepository extends GenericRepository<Documento>{

	@Query(value = "SELECT e from Documento e where e.morador.id = ?1 ")
	List<Documento> listarContasPorMorador(Integer idMorador);

}
