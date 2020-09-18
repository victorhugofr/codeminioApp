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
  @SequenceGenerator(name="SEQ_AVISO", sequenceName="id_seq_aviso", allocationSize=1)
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String texto;

  @ManyToOne
  @JoinColumn(name="id_usuario")
  private Usuario autor;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getTexto() {
	return texto;
}

public void setTexto(String texto) {
	this.texto = texto;
}

public Usuario getAutor() {
	return autor;
}

public void setAutor(Usuario autor) {
	this.autor = autor;
}
  
}