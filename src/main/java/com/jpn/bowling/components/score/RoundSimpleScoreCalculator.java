package com.jpn.bowling.components.score;

import org.springframework.stereotype.Component;

import com.jpn.bowling.domain.Round;

@Component
public class RoundSimpleScoreCalculator implements RoundScoreCalculator {

	public int calculate(java.util.List<Round> rounds, int index, Round round) {
		return rounds.get(index-1).getAccumulatedScore();
	}
}
