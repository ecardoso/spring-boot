package br.com.springbootexercicio.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractController {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(Exception e) {
		ModelAndView modelAndView = new ModelAndView("default");
		modelAndView.addObject("erro", e.getMessage());

		return modelAndView;
	}

	public ModelAndView addMensagemAplicacao(String mensagem) {
		ModelAndView modelAndView = new ModelAndView("default");
		return addMensagemAplicacao(modelAndView, mensagem);
	}

	public ModelAndView addMensagemAplicacao(ModelAndView modelAndView, String mensagem) {
		modelAndView.addObject("mensagem", mensagem);
		return modelAndView;
	}
}
