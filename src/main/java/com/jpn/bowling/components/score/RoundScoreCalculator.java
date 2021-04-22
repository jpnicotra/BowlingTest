package com.jpn.bowling.components.score;

import com.jpn.bowling.domain.Round;

/**
 * Generic interface used to calculate final score for an specific bowling round
 * 
 * @author jnicotra
 */
public interface RoundScoreCalculator {

	/**
	 * Generic interface used to calculate final score for an specific bowling round
	 * 
	 * @param rounds List of rounds
	 * @param index  Indicates round number in the list
	 * @param round  Bowling round
	 * @return int Final score of the round
	 */
	public int calculate(java.util.List<Round> rounds, int index, Round round);
}
