package br.eti.evertoncustodio.votenolivro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eti.evertoncustodio.votenolivro.model.Ranking;
import br.eti.evertoncustodio.votenolivro.model.Usuario;
import br.eti.evertoncustodio.votenolivro.model.VotosUsuario;
import br.eti.evertoncustodio.votenolivro.model.dao.UsuarioDAO;
import br.eti.evertoncustodio.votenolivro.model.dao.VotosUsuarioDAO;
import br.eti.evertoncustodio.votenolivro.model.service.RankingService;

@Controller
public class UsuarioController {

	@Autowired
	private RankingService rankingService;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private VotosUsuarioDAO votosUsuarioDAO;
	
	@Transactional
	@RequestMapping("/confirmar")
	public String confirmar(Model model, HttpSession session, Usuario usuario) {
		VotosUsuario votosUsuario = (VotosUsuario) session.getAttribute("votosUsuario");
		
		if(votosUsuario == null) {
			throw new NullPointerException("variável votosUsuario não foi gravada na sessão");
		}
		
		votosUsuario.setUsuario(usuario);
		
		usuarioDAO.gravar(usuario);
		votosUsuarioDAO.gravar(votosUsuario);
		
		Ranking ranking = rankingService.getRankingGeral();
		model.addAttribute("ranking", ranking);
		model.addAttribute("usuario", usuario);
		
		return "resultado";
	}
	
	public void setRankingService(RankingService rankingService) {
		this.rankingService = rankingService;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void setVotosUsuarioDAO(VotosUsuarioDAO votosUsuarioDAO) {
		this.votosUsuarioDAO = votosUsuarioDAO;
	}
}
