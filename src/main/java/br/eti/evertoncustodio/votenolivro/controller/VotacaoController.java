package br.eti.evertoncustodio.votenolivro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.eti.evertoncustodio.votenolivro.model.Livro;
import br.eti.evertoncustodio.votenolivro.model.Votacao;

@Controller
public class VotacaoController {
	
	@Transactional
	@RequestMapping("votar.do")
	public String votar(Model model, HttpSession session, @RequestParam("id") Livro livroSelecionado) {
		Votacao votacao = (Votacao) session.getAttribute("votacao");
		votacao.atual().votarNo(livroSelecionado);
		
		if(votacao.estaTerminada()) {
			return "usuario";
		}
		
		model.addAttribute("opcao1", votacao.atual().getOpcoes().get(0));
		model.addAttribute("opcao2", votacao.atual().getOpcoes().get(1));
		
		return "opcoes";
	}
}
