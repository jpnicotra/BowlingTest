package com.jpn.bowling.components.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RoundsBuilder;
import com.jpn.bowling.domain.Round;
import com.jpn.bowling.exceptions.NotEnoughRollsException;
import com.jpn.games.components.PlayerScoreCalculator;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

@Component
public class RoundsBuilderImpl implements RoundsBuilder {

	@Autowired
	private PlayerScoreCalculator bowlingPlayerScoreCalculator;
	
	public PlayerInfo buildRounds(PlayerInfo player) throws GameException {
		Round currentRound = null;
		java.util.List<Round> rounds = new java.util.ArrayList<Round>();
		final java.util.List<String> scores = player.getScores();
		for (String score : scores) {
			if (currentRound==null) {
				currentRound = new Round(rounds.size()+1);
				rounds.add(currentRound);
			}
			currentRound.addScore(score);
			
			if (currentRound.isFull()) {
				currentRound = null;
			}
		}
		
		player.setRounds(rounds);
		// TODO Solve error message
		if (rounds.size()!=BowlingGame.numberOfRounds)
			throw new NotEnoughRollsException();

		// If there was not error with this round, for each on we have to calculate the accumulated Score
		if (player.getErrorMessage()==null) {
			rounds.forEach(round -> round.setAccumulatedScore(bowlingPlayerScoreCalculator.calculateScore(player,round)));
		}
		
		return player;
	}

}
