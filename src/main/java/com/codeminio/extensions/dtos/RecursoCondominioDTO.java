package com.codeminio.extensions.dtos;

import com.codeminio.dtos.RecursoDTO;

public class RecursoCondominioDTO extends RecursoDTO {
    private String nomeDaArea;

    public String getNomeDaArea() {
        return this.nomeDaArea;
    }

    public void setNomeDaArea(String nomeDaArea) {
        this.nomeDaArea = nomeDaArea;
    }
}
