package com.codeminio.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Enquete extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AVISO")
    @SequenceGenerator(name = "SEQ_AVISO", sequenceName = "id_seq_aviso", allocationSize = 1)
    private Integer id;

    @Column()
    private String titulo;

    @Column()
    private LocalDate dataLimite;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario criador;

    @OneToMany(mappedBy = "enquete")
    private List<Alternativa> alternativas;

    public Enquete() {
        this.alternativas = new ArrayList<Alternativa>();
    }

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

    public LocalDate getDataLimite() {
        return this.dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Usuario getCriador() {
        return this.criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public List<Alternativa> getAlternativas() {
        return this.alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public void setAlternativas(Alternativa alternativa) {
        this.alternativas.add(alternativa);
    }
}
