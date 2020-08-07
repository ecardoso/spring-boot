package br.com.springbootexercicio.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springbootexercicio.model.Despesa;

public interface Despesas extends JpaRepository<Despesa, Long> {

	default List<Despesa> findAllByCategoria() {
		return findAll(new Sort(Sort.Direction.ASC, "categoria"));
	}
}
