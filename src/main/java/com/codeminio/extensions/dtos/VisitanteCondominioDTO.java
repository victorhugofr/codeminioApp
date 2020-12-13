package com.codeminio.extensions.dtos;

public class VisitanteCondominioDTO {
    private String cpf;
    private String nome;

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

}
