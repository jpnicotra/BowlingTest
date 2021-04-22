package com.jpn.bowling.components.score;

import org.springframework.stereotype.Component;

import com.jpn.bowling.domain.Round;

/**
 * Specific calculator for a spare round
 * 
 * @author jnicotra
 */
@Component
public class RoundSpareScoreCalculator implements RoundScoreCalculator {

	/**
	 * Specific calculator for a spare round
	 * 
	 * @param rounds List of rounds
	 * @param index  Indicates round number in the list
	 * @param round  Bowling round
	 * @return int Final score of the round
	 */
	public int calculate(java.util.List<Round> rounds, int index, Round round) {
		int score = 0;

		if (round.isLastRound()) {
			score += round.getPoints().get(round.getPoints().size() - 1);
		} else {
			Round addRound = rounds.get(index + 1);
			score += addRound.getPoints().get(0);
		}

		return score;
	}
}
