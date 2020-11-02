package com.codeminio.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Classificados extends AuditedEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RECLAMACAO")
	@SequenceGenerator(name = "SEQ_RECLAMACAO", sequenceName = "id_seq_reclamacao", allocationSize = 1)
	private Integer id;

	@Column(columnDefinition = "TEXT")
	private String texto;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario locador;
	
	@Column()
	private Usuario locatario;
	
	@Column()
	private String preco;
	
	@Column()
	private String categoria;

	@Column()
	private String apartamento;
	
	@Column()
	private String telefone;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Usuario getLocador() {
		return locador;
	}
	
	public void setLocador(Usuario locador) {
		this.locador = locador;
	}
	
	public Usuario getLocatario() {
		return locatario;
	}
	
	public void setLocatario(Usuario locatario) {
		this.locatario = locatario;
	}
	
	public String getPreco() {
		return preco;
	}
	
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getApartamento() {
		return apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

}
