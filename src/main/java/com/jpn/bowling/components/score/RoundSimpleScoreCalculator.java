package com.jpn.bowling.components.score;

import org.springframework.stereotype.Component;

import com.jpn.bowling.domain.Round;

/**
 * Specific calculator for a simple round (No spare, no Strike)
 * 
 * @author jnicotra
 */
@Component
public class RoundSimpleScoreCalculator implements RoundScoreCalculator {

	/**
	 * Specific calculator for a simple round (No spare, no Strike)
	 * 
	 * @param rounds List of rounds
	 * @param index  Indicates round number in the list
	 * @param round  Bowling round
	 * @return int Final score of the round
	 */
	public int calculate(java.util.List<Round> rounds, int index, Round round) {
		return rounds.get(index - 1).getAccumulatedScore();
	}
}
