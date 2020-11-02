package com.codeminio.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeminio.dominio.Documento;
import com.codeminio.repository.DocumentoRepository;
import com.codeminio.service.DocumentoService;

@Service
public class DocumentoServiceImpl implements DocumentoService{

	@Autowired
	private DocumentoRepository repository;
	
	@Override
	public void salvarDocumento(Documento documento) {
		try {
			documento.setArquivoBase64Original(documento.getDocumento().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		repository.save(documento);
	}
	
	@Override
	public List<Documento> listarDocumentos() {
		return (List<Documento>) repository.findAll();
	}
	
	public Optional<Documento> procurarPorId(Integer id) {
		return repository.findById(id);
	}
	
	public List<Documento> listarContasPorMorador(Integer idMorador){
		return repository.listarContasPorMorador(idMorador);
	}

}
