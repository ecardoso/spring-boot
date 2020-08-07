package br.com.springbootexercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootexercicio.data.model.Pessoa;
import br.com.springbootexercicio.service.PessoaService;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Pessoa EndPoint", tags = { "PessoaEndPoint" })
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PagedResourcesAssembler<Pessoa> assembler;

	@GetMapping(value = "/findAllPessoa", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAllPessoa(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {
		Direction sortDirection = Direction.DESC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));

		Page<Pessoa> pessoas = pessoaService.findAll(pageable);
		PagedResources<?> resources = assembler.toResource(pessoas);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@GetMapping(value = "/findByName", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findByName(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "12") int limit,
							@RequestParam(value = "nome") String nome) {
		Direction sortDirection = Direction.DESC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));

		Page<Pessoa> pessoas = pessoaService.findByName(nome, pageable);
		PagedResources<?> resources = assembler.toResource(pessoas);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

}
