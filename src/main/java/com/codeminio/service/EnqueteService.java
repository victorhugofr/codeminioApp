package com.codeminio.service;

import java.util.List;

import com.codeminio.dominio.Enquete;
import com.codeminio.dtos.EnqueteDTO;

public interface EnqueteService {

    List<Enquete> index();

    Enquete show(String username, int id);

    void update(String username, int idEnquete, Integer idAlternativa);

    boolean checkIfVoted(String username, int idEnquete);

    void store(String username, EnqueteDTO enqueteDTO);
}
