package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Enquete;
import com.codeminio.dtos.EnqueteDTO;

public interface EnqueteService {

    List<Enquete> index();

    Enquete show(String username, int id);

    void update(String username, int idEnquete, int idAlternativa);

    void store(String username, EnqueteDTO enqueteDTO);
}
