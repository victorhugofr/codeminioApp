package com.codeminio.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Alternativa extends AuditedEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AVISO")
    @SequenceGenerator(name = "SEQ_AVISO", sequenceName = "id_seq_aviso", allocationSize = 1)
    private Integer id;

    @Column()
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_enquete")
    private Enquete enquete;

    @ManyToMany
    private List<Usuario> votantes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Enquete getEnquete() {
        return this.enquete;
    }

    public void setEnquete(Enquete enquete) {
        this.enquete = enquete;
    }

    public List<Usuario> getVotantes() {
        return this.votantes;
    }

    public void setVotantes(List<Usuario> votantes) {
        this.votantes = votantes;
    }

    public void setVotantes(Usuario votante) {
        this.votantes.add(votante);
    }
}
