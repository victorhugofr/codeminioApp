package com.codeminio.dtos;

public abstract class ReservaDTO {
    private String data;
    private int idRecurso;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getIdRecurso() {
        return this.idRecurso;
    }
}
