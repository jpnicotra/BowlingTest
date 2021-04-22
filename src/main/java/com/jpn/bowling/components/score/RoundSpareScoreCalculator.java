package com.jpn.bowling.components.score;

import org.springframework.stereotype.Component;

import com.jpn.bowling.domain.Round;

@Component
public class RoundSpareScoreCalculator implements RoundScoreCalculator {

	public int calculate(java.util.List<Round> rounds, int index, Round round) {
		int score = 0;
		
		if (round.isLastRound()) {
			score+=round.getPoints().get(round.getPoints().size()-1);
		}
		else {
			Round addRound = rounds.get(index+1);
			score+=addRound.getPoints().get(0);
		}
		
		return score;
	}
}
