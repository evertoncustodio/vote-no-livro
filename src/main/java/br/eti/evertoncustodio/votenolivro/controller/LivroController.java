package br.eti.evertoncustodio.votenolivro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;

@Controller
public class LivroController {

	@Autowired
	private LivroDAO livroDAO;
	
	@RequestMapping("mostrarLivro.do")
	public String mostrarLivro(Model model, Long id) {
		Livro livro = livroDAO.getLivro(id);
		model.addAttribute("livro", livro);
		
		return "livro";
	}

	public void setLivroDAO(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}
}
