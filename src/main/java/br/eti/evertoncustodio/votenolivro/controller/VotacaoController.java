package br.eti.evertoncustodio.votenolivro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;

@Controller
public class VotacaoController {
	
	@Autowired
	private LivroDAO livroDAO;
	
	@Transactional
	@RequestMapping("/votar")
	public String votar(Model model, HttpSession session, Long id) {
		Livro livroSelecionado = livroDAO.getLivro(id);
		
		Votacao votacao = (Votacao) session.getAttribute("votacao");
		votacao.atual().votarNo(livroSelecionado);
		
		if(votacao.estaTerminada()) {
			return "usuario";
		}
		
		model.addAttribute("opcao1", votacao.atual().getOpcoes().get(0));
		model.addAttribute("opcao2", votacao.atual().getOpcoes().get(1));
		
		return "opcoes";
	}

	public void setLivroDAO(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}
}
