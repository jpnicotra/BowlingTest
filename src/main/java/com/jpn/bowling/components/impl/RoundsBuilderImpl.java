package com.jpn.bowling.components.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RoundsBuilder;
import com.jpn.bowling.components.score.ScoreController;
import com.jpn.bowling.domain.BowlingPlayerInfo;
import com.jpn.bowling.domain.Round;
import com.jpn.bowling.exceptions.NotEnoughRollsException;
import com.jpn.games.components.PlayerScoreCalculator;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

/**
 * Implementation that defines how scores sent by the users are transformed in
 * Rounds and returns new list of rounds
 * 
 * @author jnicotra
 */
@Component
public class RoundsBuilderImpl implements RoundsBuilder {

	@Autowired
	private PlayerScoreCalculator bowlingPlayerScoreCalculator;
	@Autowired
	private ScoreController bowlingScoreController;

	/**
	 * Implementation that defines how scores sent by the users are transformed in
	 * Rounds and returns new list of rounds
	 * 
	 * @param player Player that holds all string scores
	 * @return List of Rounds
	 * @see Round
	 * @see PlayerInfo
	 */
	public List<Round> buildRounds(BowlingPlayerInfo player) throws GameException {
		Round currentRound = null;
		java.util.List<Round> rounds = new java.util.ArrayList<Round>();
		final java.util.List<String> scores = player.getScores();
		for (String score : scores) {
			if (currentRound == null) {
				currentRound = new Round(rounds.size() + 1);
				rounds.add(currentRound);
			}
			bowlingScoreController.addScore(currentRound, score);

			if (currentRound.isFull()) {
				currentRound = null;
			}
		}

		player.setRounds(rounds);

		if (rounds.size() != BowlingGame.numberOfRounds)
			throw new NotEnoughRollsException();

		// If there was not error with this round, for each on we have to calculate the
		// accumulated Score
		if (player.getErrorMessage() == null) {
			rounds.forEach(
					round -> round.setAccumulatedScore(bowlingPlayerScoreCalculator.calculateScore(player, round)));
		}

		return rounds;
	}

}
