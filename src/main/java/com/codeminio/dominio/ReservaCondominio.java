package com.codeminio.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class ReservaCondominio extends Reserva {

	@ManyToMany
	private List<Visita> visitantes;
	
	public List<Visita> getVisitantes() {
		return this.visitantes;
	}

	public void setVisitantes(List<Visita> visitantes) {
		this.visitantes = visitantes;
	}
	
}
