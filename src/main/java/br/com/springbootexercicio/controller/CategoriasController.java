package br.com.springbootexercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.springbootexercicio.model.Categoria;
import br.com.springbootexercicio.repository.Categorias;

@Controller
@RequestMapping("/categorias")
public class CategoriasController extends AbstractController {

	@Autowired
	private Categorias categorias;

	private String acao;

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("categorias");
		modelAndView.addObject("categorias", categorias.findAll());

		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView novo() {
		acao = "Cadastro";
		return getCategoria(null);
	}

	@GetMapping("/editar")
	public ModelAndView editar(@RequestParam(value = "id", required = false) Long id) {
		acao = "Atualização";

		Categoria categoria = categorias.getOne(id);
		return getCategoria(categoria);
	}

	@GetMapping("/remover")
	public ModelAndView remover(@RequestParam(value = "id", required = false) Long id) {
		Categoria categoria = categorias.getOne(id);
		categorias.delete(categoria);

		ModelAndView modelAndView = listar();
		mensagem(modelAndView, categoria, "Exclusão");
		return modelAndView;
	}

	@PostMapping
	public ModelAndView salvar(Categoria categoria) {
		categorias.save(categoria);

		ModelAndView modelAndView = getCategoria(null);
		mensagem(modelAndView, categoria, acao);
		return modelAndView;
	}

	private ModelAndView getCategoria(Categoria categoria) {
		ModelAndView modelAndView = new ModelAndView("nova-categoria");
		modelAndView.addObject("categoria", categoria == null ? new Categoria() : categoria);
		modelAndView.addObject("acao", String.format("%s Categoria", acao));
		return modelAndView;
	}

	private void mensagem(ModelAndView modelAndView, Categoria categoria, String acao) {
		addMensagemAplicacao(modelAndView, String.format("%s realizado com sucesso. Descrição: %s", acao, categoria.getDescricao()));
	}
}
