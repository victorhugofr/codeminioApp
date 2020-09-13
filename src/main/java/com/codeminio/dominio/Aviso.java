package com.codeminio.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Aviso extends AuditedEntity{

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_AVISO")
  @SequenceGenerator(name="SEQ_AVISO", sequenceName="codeminio.id_seq_aviso", allocationSize=1)
  private Integer id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCriacao;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAtualizacao;

  @Column(columnDefinition = "TEXT")
  private String texto;

  @ManyToOne
  @JoinColumn(name="id_funcionario")
  private Funcionario autor;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Date getDataCriacao() {
	return dataCriacao;
}

public void setDataCriacao(Date dataCriacao) {
	this.dataCriacao = dataCriacao;
}

public Date getDataAtualizacao() {
	return dataAtualizacao;
}

public void setDataAtualizacao(Date dataAtualizacao) {
	this.dataAtualizacao = dataAtualizacao;
}

public String getTexto() {
	return texto;
}

public void setTexto(String texto) {
	this.texto = texto;
}

public Funcionario getAutor() {
	return autor;
}

public void setAutor(Funcionario autor) {
	this.autor = autor;
}
  
}