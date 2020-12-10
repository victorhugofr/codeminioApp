package com.codeminio.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public abstract class Reserva extends AuditedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESERVA")
    @SequenceGenerator(name = "SEQ_RESERVA", sequenceName = "id_seq_reserva", allocationSize = 1)
    private Integer id;

    @Column
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // @ManyToOne
    // @JoinColumn(name = "id_recurso")
    // private Recurso recurso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.data = LocalDate.parse(data, formatter);
        } catch (Exception e) {
            this.data = null;
        }
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // public Recurso getRecurso() {
    // return this.recurso;
    // }

    // public void setRecurso(Recurso recurso) {
    // this.recurso = recurso;
    // }
}
