package br.eti.evertoncustodio.votenolivro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eti.evertoncustodio.votenolivro.model.Livro;

@Controller
public class LivroController {

	@RequestMapping("mostrarLivro.do")
	public String mostrarLivro(@ModelAttribute Livro livro) {
		return "livro";
	}
}
