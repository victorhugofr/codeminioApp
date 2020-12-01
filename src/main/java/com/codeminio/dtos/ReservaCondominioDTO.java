package com.codeminio.dtos;

import java.util.List;

public class ReservaCondominioDTO {
    private String data;
    private String idArea;
    private List<VisitanteDTO> visitantes;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public String getIdArea() {
        return this.idArea;
    }

    public void setVisitantes(List<VisitanteDTO> visitantes) {
        this.visitantes = visitantes;
    }

    public List<VisitanteDTO> getVisitantes() {
        return this.visitantes;
    }
}
