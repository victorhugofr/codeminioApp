package com.codeminio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codeminio.dominio.Morador;

@Repository
public interface MoradorRepository extends GenericRepository<Morador> {

	@Query(value = "SELECT u from Morador u ")
	 Morador findByCpf(String cpf);
	
	@Query(value = "select u from Morador u where u.usuario = (select a.id from Usuario a where a.login = ?1)")
	 Optional<Morador> findByLogin(String login);
	
	@Query(value = "select u from Morador u ")
	 Optional<Morador> findByEmail(String email);
	
	@Query(value = "select u from Morador u ")
	 Optional<Morador> findByCPF(String cpf);

//	@Query(value = "select * from Morador")
//	public List<Morador> buscarTodosMoradores();
	
	@Query(value = "select u from Morador u")
	 Morador findByCodigoRecuperarSenha(String codigoRecuperarSenha);

}
