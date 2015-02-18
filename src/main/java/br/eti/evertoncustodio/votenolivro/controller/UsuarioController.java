package br.eti.evertoncustodio.votenolivro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eti.evertoncustodio.votenolivro.model.Usuario;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.dao.VotacaoDAO;
import br.eti.evertoncustodio.votenolivro.model.service.ICriadorDeRanking;

@Controller
public class UsuarioController {
	
	@Autowired
	private VotacaoDAO votacoes;
	@Autowired
	private ICriadorDeRanking criadorDeRanking;
	
	@Transactional
	@RequestMapping("confirmar.do")
	public String confirmar(Model model, HttpSession session, Usuario usuario) {
		Votacao votacao = (Votacao) session.getAttribute("votacao");
		votacao.setUsuario(usuario);
		votacoes.gravar(votacao);
		
		model.addAttribute("rankingGeral", criadorDeRanking.geral());
		model.addAttribute("rankingUsuario", criadorDeRanking.por(usuario));
		
		return "resultado";
	}

	public void setVotacoes(VotacaoDAO votacoes) {
		this.votacoes = votacoes;
	}

	public void setCriadorDeRanking(ICriadorDeRanking criadorDeRanking) {
		this.criadorDeRanking = criadorDeRanking;
	}
}
