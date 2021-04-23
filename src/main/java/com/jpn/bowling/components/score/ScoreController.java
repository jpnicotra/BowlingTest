package com.jpn.bowling.components.score;

import com.jpn.bowling.domain.Round;
import com.jpn.games.exceptions.GameException;

/**
 * Defines how scores are pre-validated and added to the round
 * 
 * @author jnicotra
 */
public interface ScoreController {

	/**
	 * Defines how scores are pre-validated and added to the round
	 * 
	 * @param round Round
	 * @param score Score
	 * @see Round
	 */
	public void addScore(Round round, String score) throws GameException;
}
