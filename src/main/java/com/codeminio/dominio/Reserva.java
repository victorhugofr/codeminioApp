package com.codeminio.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Reserva extends AuditedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AVISO")
    @SequenceGenerator(name = "SEQ_AVISO", sequenceName = "id_seq_aviso", allocationSize = 1)
    private Integer id;

    @Column
    private String nomeDaArea;

    @Column
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Visita> visitantes;
 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeDaArea() {
        return this.nomeDaArea;
    }

    public void setNomeDaArea(String nomeDaArea) {
        this.nomeDaArea = nomeDaArea;
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

    public List<Visita> getVisitantes() {
        return this.visitantes;
    }

    public void setVisitantes(List<Visita> visitantes) {
        this.visitantes = visitantes;
    }
}
