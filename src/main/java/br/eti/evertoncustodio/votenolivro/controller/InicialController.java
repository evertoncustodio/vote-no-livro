package br.eti.evertoncustodio.votenolivro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eti.evertoncustodio.votenolivro.model.Opcao;
import br.eti.evertoncustodio.votenolivro.model.Votacao;
import br.eti.evertoncustodio.votenolivro.model.VotosUsuario;
import br.eti.evertoncustodio.votenolivro.model.service.OpcaoService;

@Controller
public class InicialController {
	
	@Autowired
	private OpcaoService opcaoService;
	
	@RequestMapping("/")
	public String iniciar(Model model, HttpSession session) {
		List<Opcao> opcoes = opcaoService.getOpcoes();
		Votacao votacao = new Votacao(opcoes);
		
		Opcao opcao = votacao.atual();
		model.addAttribute("opcao1", opcao.getOpcao1());
		model.addAttribute("opcao2", opcao.getOpcao2());
		
		session.setAttribute("votacao", votacao);
		
		VotosUsuario votosUsuario = new VotosUsuario();
		session.setAttribute("votosUsuario", votosUsuario);
		
		return "principal";
	}

	public void setOpcaoService(OpcaoService opcaoService) {
		this.opcaoService = opcaoService;
	}
}
