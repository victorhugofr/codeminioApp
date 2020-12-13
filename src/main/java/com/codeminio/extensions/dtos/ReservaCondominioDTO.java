package com.codeminio.extensions.dtos;

import java.util.List;

import com.codeminio.dtos.ReservaDTO;

public class ReservaCondominioDTO extends ReservaDTO {

    private List<VisitanteCondominioDTO> visitantes;

    public void setVisitantes(List<VisitanteCondominioDTO> visitantes) {
        this.visitantes = visitantes;
    }

    public List<VisitanteCondominioDTO> getVisitantes() {
        return this.visitantes;
    }
}
