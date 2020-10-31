package com.codeminio.service;

import com.codeminio.dtos.EnqueteDTO;

public interface EnqueteService {

    public void store(String username, EnqueteDTO enqueteDTO);
}
