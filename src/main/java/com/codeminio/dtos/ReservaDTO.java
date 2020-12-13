package com.codeminio.dtos;

public abstract class ReservaDTO {
    private String data;
    private String idRecurso;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getIdRecurso() {
        return this.idRecurso;
    }
}
