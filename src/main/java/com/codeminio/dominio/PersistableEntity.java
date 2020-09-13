package com.codeminio.dominio;

import java.io.Serializable;

/**
 * Interface base para as classes de entidade.
 * 
 * @author victor
 *
 * @param <PK>
 */
public interface PersistableEntity<PK extends Serializable> {
	PK getId();
	void setId(PK id);
}
