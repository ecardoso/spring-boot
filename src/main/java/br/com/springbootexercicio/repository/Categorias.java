package br.com.springbootexercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springbootexercicio.model.Categoria;

public interface Categorias extends JpaRepository<Categoria, Long> {
	//...
}
