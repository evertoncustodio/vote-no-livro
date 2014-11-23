package br.eti.evertoncustodio.votenolivro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eti.evertoncustodio.votenolivro.model.Opcao;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.VotosUsuario;
import br.eti.evertoncustodio.votenolivro.model.dao.LivroDAO;
import br.eti.evertoncustodio.votenolivro.model.service.ScoreService;

@Controller
public class VotacaoController {

	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private LivroDAO livroDAO;
	
	@Transactional
	@RequestMapping("/votar")
	public String votar(Model model, HttpSession session, Long id) {
		VotosUsuario votosUsuario = (VotosUsuario) session.getAttribute("votosUsuario");
		
		if(votosUsuario == null) {
			throw new NullPointerException("variável votosUsuario não foi gravada na sessão");
		}
		
		scoreService.votar(id);
		votosUsuario.adicionarVoto(livroDAO.getLivro(id));
		
		Votacao votacao = (Votacao) session.getAttribute("votacao");
		
		if(votacao.temProximo()) {
			preencherOpcoes(model, votacao.proximo());
			return "opcoes";
		} else {
			return "usuario";
		}
	}
	
	private void preencherOpcoes(Model model, Opcao opcao) {
		model.addAttribute("opcao1", opcao.getOpcao1());
		model.addAttribute("opcao2", opcao.getOpcao2());
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public void setLivroDAO(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}
}
