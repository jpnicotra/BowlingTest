package com.jpn.bowling.exceptions;

import com.jpn.bowling.components.impl.BowlingGame;
import com.jpn.bowling.domain.Round;

public class ScoreOutOfRangeException extends RoundException {

	public ScoreOutOfRangeException(Round round, String score) {
		super(round, "Score (" + score + ") in round " + round.getNumber() + " it's out of range (0-"
				+ BowlingGame.maxShotValue + ")");
	}
}
