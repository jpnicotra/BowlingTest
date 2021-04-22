package com.jpn.bowling.components.score;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpn.bowling.domain.Round;
import com.jpn.bowling.domain.Round.RoundType;
import com.jpn.games.components.PlayerScoreCalculator;
import com.jpn.games.domain.PlayerInfo;

@Component
public class BowlingPlayerScoreCalculator implements PlayerScoreCalculator {

	@Autowired
	private RoundScoreCalculator roundSimpleScoreCalculator;
	@Autowired
	private RoundScoreCalculator roundSpareScoreCalculator;
	@Autowired
	private RoundScoreCalculator roundStrikeScoreCalculator;
	
	@Override
	public int calculateScore(PlayerInfo player, Round round) {
		final List<Round> rounds = player.getRounds();
		int score = 0;
		final int index = round.getNumber()-1;

		if (index>0) {
			score += roundSimpleScoreCalculator.calculate(rounds, index, round);
		}
		
		if (round.getRoundType()==RoundType.STRIKE) {
			score += roundStrikeScoreCalculator.calculate(rounds, index, round);
		}
		if (round.getRoundType()==RoundType.SPARE) {
			score += roundSpareScoreCalculator.calculate(rounds, index, round);
		}
		score+=round.getFinalScore();

		return score;
	}
	
}
