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
public class Visita extends AuditedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VISITA")
	@SequenceGenerator(name = "SEQ_VISITA", sequenceName = "id_seq_visita", allocationSize = 1)
	private Integer id;
	
	@Column
	private String nome;
	
	@Column
	private Boolean ativo;
	
	@Column
	private String cpf;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dataCriacao;
	
	
//	private String apartamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

//	public Date getDataCriacao() {
//		return dataCriacao;
//	}
//
//	public void setDataCriacao(Date dataCriacao) {
//		this.dataCriacao = dataCriacao;
//	}
//	
//	public String getApartamento() {
//		return apartamento;
//	}
//
//	public void setApartamento(String apartamento) {
//		this.apartamento = apartamento;
//	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
