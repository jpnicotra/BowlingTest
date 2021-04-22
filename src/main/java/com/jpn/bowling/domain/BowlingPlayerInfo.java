package com.jpn.bowling.domain;

import java.util.List;

import com.jpn.games.domain.PlayerInfo;

/**
 * Information about bowling player that includes common functionality as player
 * name, but includes aditional and specific information like Rounds.
 * 
 * @author jnicotra
 */
public class BowlingPlayerInfo extends PlayerInfo {
	private final List<String> scores;
	private java.util.List<Round> rounds;

	public BowlingPlayerInfo(String playerName, List<String> scores) {
		super(playerName);
		this.scores = scores;
	}

	/**
	 * Get scores in a String representation (0-10, F, X, /)
	 * 
	 * @return List of strings that represents Scores
	 */
	public List<String> getScores() {
		return scores;
	}

	/**
	 * Get list of rounds
	 * 
	 * @return List of Round
	 * @see Round
	 */
	public java.util.List<Round> getRounds() {
		return rounds;
	}

	/**
	 * Set list of rounds
	 * 
	 * @param rounds List of Round
	 * @see Round
	 */
	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;

	}

	/**
	 * Get final score of this Bowling Player
	 * 
	 * @return Final score!
	 */
	public int getFinalScore() {
		int finalScore = 0;
		if (rounds.size() > 0)
			finalScore = rounds.get(rounds.size() - 1).getAccumulatedScore();

		return finalScore;
	}
}
