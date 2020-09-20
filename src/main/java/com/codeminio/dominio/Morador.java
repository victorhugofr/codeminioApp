package com.codeminio.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Morador extends AuditedEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MORADOR")
	@SequenceGenerator(name = "SEQ_MORADOR", sequenceName = "id_seq_morador", allocationSize = 1)
	private Integer id;

	private String apartamento;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Morador() {
		this.usuario = new Usuario();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApartamento() {
		return apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((apartamento == null) ? 0 : apartamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Morador other = (Morador) obj;
		if (apartamento == null) {
			if (other.apartamento != null)
				return false;
		} else if (!apartamento.equals(other.apartamento))
			return false;
		return true;
	}

}
