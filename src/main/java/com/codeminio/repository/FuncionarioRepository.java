package com.codeminio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codeminio.dominio.Funcionario;

@Repository
public interface FuncionarioRepository extends GenericRepository<Funcionario> {

	@Query(value = "SELECT u FROM Funcionario u ")
	public Funcionario findByCpf(String cpf);
	
	@Query(value = "select u from Funcionario u")
	public Optional<Funcionario> findByLogin(String login);
	
	@Query(value = "select u from Funcionario u ")
	public Optional<Funcionario> findByEmail(String email);

	@Query(value = "select u from Funcionario u ")
	public Funcionario findByCodigoRecuperarSenha(String codigoRecuperarSenha);

}
