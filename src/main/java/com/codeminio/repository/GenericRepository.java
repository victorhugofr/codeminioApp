package com.codeminio.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.codeminio.dominio.AuditedEntity;


@NoRepositoryBean
public interface GenericRepository<T extends AuditedEntity> extends JpaRepository<T, Integer> {

	@Override
	@Query(value = "select e from #{#entityName} e where e.ativo = true")
	List<T> findAll();

	@Override
	@Query(value = "select e from #{#entityName} e where e.id = ?1 and e.ativo = true")
	Optional<T> findById(Integer id);

/*
	@Override
	@Transactional
	@Modifying
	@Query(value = "UPDATE #{#entityName} SET ativo=false where id = ?1")
	void deleteById(Integer id);
*/
	
	@Override
	@Transactional
	default void deleteById(Integer id) {
		Optional<T> entity = findById(id);
		entity.get().setAtivo(false);
		save(entity.get());
	}	

	@Override
	@Transactional
	default void delete(T obj) {
		obj.setAtivo(false);
		save(obj);
	}
	
/*
	@Override
	@Transactional
	@Modifying
	@Query(value = "UPDATE #{#entityName} e SET e.ativo=false where e = ?1")
	void delete(T entity);
*/
	
	@Override
	default void deleteAll(Iterable<? extends T> arg0) {
		arg0.forEach(entity -> {
			deleteById(entity.getId());
		});
	}
	
	@Query(value="select count(e) from #{#entityName} e where e.ativo = true")
	long countAtivo();

}
