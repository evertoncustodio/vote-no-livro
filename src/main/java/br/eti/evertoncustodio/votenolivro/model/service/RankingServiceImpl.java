package br.eti.evertoncustodio.votenolivro.model.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.eti.evertoncustodio.votenolivro.model.Ranking;
import br.eti.evertoncustodio.votenolivro.model.Score;

@Service
class RankingServiceImpl implements RankingService {

	@Resource
	private ScoreService scoreService;
	
	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public Ranking getRankingGeral() {
		List<Score> scores = scoreService.getLista();
		
		if(scores == null || scores.isEmpty()) {
			return Ranking.VAZIO;
		}
		
		ordenacaoDecrescente(scores);
		
		return new Ranking(scores);
	}

	private void ordenacaoDecrescente(List<Score> scores) {
		Collections.sort(scores, new Comparator<Score>() {
			public int compare(Score o1, Score o2) {
				return o2.getVotos() - o1.getVotos();
			}
		});
	}
}
