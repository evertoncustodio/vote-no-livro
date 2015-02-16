package br.eti.evertoncustodio.votenolivro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.service.ICriadorDeVotacoes;

@Controller
public class InicialController {
	
	@Autowired
	private ICriadorDeVotacoes criadorDeVotacoes;
	
	@RequestMapping("/")
	public String iniciar(Model model, HttpSession session) {
		Votacao votacao = criadorDeVotacoes.nova();
		
		model.addAttribute("opcao1", votacao.atual().getOpcoes().get(0));
		model.addAttribute("opcao2", votacao.atual().getOpcoes().get(1));
		
		session.setAttribute("votacao", votacao);
		
		return "principal";
	}

	public void setCriadorDeVotacoes(ICriadorDeVotacoes criadorDeVotacoes) {
		this.criadorDeVotacoes = criadorDeVotacoes;
	}
}
