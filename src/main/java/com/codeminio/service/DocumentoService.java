package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Documento;

public interface DocumentoService {

	List<Documento> listarDocumentos();
	void salvarDocumento(Documento documento);
}
