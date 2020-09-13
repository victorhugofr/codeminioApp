package com.codeminio.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Funcionario extends AuditedEntity{

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_FUNCIONARIO")
  @SequenceGenerator(name="SEQ_FUNCIONARIO", sequenceName="id_seq_funcionario", allocationSize=1)
  private Integer id;
  
  @JsonBackReference
  @OneToMany(mappedBy = "autor")
  private List<Aviso> avisos;
  
  @ManyToOne
  @JoinColumn(name="id_usuario")
  private Usuario usuario;

  public Funcionario() {
		this.usuario = new Usuario();
	}
  public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Usuario getUsuario() {
	return usuario;
}

public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}

public List<Aviso> getAvisos() {
    return this.avisos;
  }

  public void setAvisos(List<Aviso> avisos) {
    this.avisos = avisos;
  }

  public Aviso getAviso(Integer idAviso) {
    for (Aviso aviso : this.avisos) {
      if (aviso.getId() == idAviso) {
        return aviso;
      }
    }

    return null;
  }
}
