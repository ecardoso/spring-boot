package br.com.springbootexercicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.springbootexercicio.data.model.Pessoa;
import br.com.springbootexercicio.service.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	public Page<Pessoa> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Pessoa> findByName(String nome, Pageable pageable) {
		return repository.findByName(nome, pageable);
	}

}
