package com.codeminio.service;

import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Documento;

public interface DocumentoService {

	List<Documento> listarDocumentos();
	void salvarDocumento(Documento documento);
	public Optional<Documento> procurarPorId(Integer id);
	public List<Documento> listarContasPorMorador(Integer idMorador);
}
