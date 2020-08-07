package br.com.springbootexercicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.springbootexercicio.model.Despesa;
import br.com.springbootexercicio.repository.Categorias;
import br.com.springbootexercicio.repository.Despesas;

@Controller
@RequestMapping("/despesas")
public class DespesasController extends AbstractController {

	@Autowired
	private Despesas despesas;

	@Autowired
	private Categorias categorias;

	private String acao;

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("despesas");

		List<Despesa> allDespesas = despesas.findAllByCategoria();
		if (allDespesas.isEmpty()) {
			return addMensagemAplicacao("Não foi encontrado nenhuma despesa.");
		}

		modelAndView.addObject("despesas", allDespesas);
		modelAndView.addObject("categoria", allDespesas.stream().findFirst().get().getCategoria());

		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView novo() {
		acao = "Cadastro";

		return getDespesa(null);
	}

	@GetMapping("/editar")
	public ModelAndView editar(@RequestParam(value = "id", required = false) Long id) {
		acao = "Atualização";

		Despesa despesa = despesas.getOne(id);
		return getDespesa(despesa);
	}

	@GetMapping("/remover")
	public ModelAndView remover(@RequestParam(value = "id", required = false) Long id) {
		Despesa despesa = despesas.getOne(id);
		despesas.delete(despesa);

		ModelAndView modelAndView = listar();
		mensagem(modelAndView, "Exclusão");
		return modelAndView;
	}

	@PostMapping
	public ModelAndView salvar(Despesa despesa) {
		despesas.save(despesa);

		ModelAndView modelAndView = getDespesa(null);
		mensagem(modelAndView, acao);
		return modelAndView;
	}

	private ModelAndView getDespesa(Despesa despesa) {
		ModelAndView modelAndView = new ModelAndView("nova-despesa");
		modelAndView.addObject("despesa", despesa == null ? new Despesa() : despesa);
		modelAndView.addObject("categorias", categorias.findAll());
		modelAndView.addObject("acao", String.format("%s Despesa", acao));
		return modelAndView;
	}

	private void mensagem(ModelAndView modelAndView, String acao) {
		addMensagemAplicacao(modelAndView, String.format("%s realizado com sucesso.", acao));
	}
}
