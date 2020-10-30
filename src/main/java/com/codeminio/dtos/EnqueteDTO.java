package com.codeminio.dtos;

import java.util.ArrayList;
import java.util.List;

public class EnqueteDTO {

    private String titulo;
    private String dataLimite;
    private List<String> alternativas;

    public EnqueteDTO() {
        this.alternativas = new ArrayList<String>();
        this.alternativas.add("");
        this.alternativas.add("");
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataLimite() {
        return this.dataLimite;
    }

    public void setDataLimite(String dataLimite) {
        this.dataLimite = dataLimite;
    }

    public List<String> getAlternativas() {
        return this.alternativas;
    }

    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }

    public void setAlternativas(String alternativa) {
        this.alternativas.add(alternativa);
    }
}
