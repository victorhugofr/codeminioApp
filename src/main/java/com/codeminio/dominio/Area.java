package com.codeminio.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Area extends Recurso {

    @Column(unique = true)
    private String nomeDaArea;

    public void setNomeDaArea(String nomeDaArea) {
        this.nomeDaArea = nomeDaArea;
    }

    public String getNomeDaArea() {
        return this.nomeDaArea;
    }
}
