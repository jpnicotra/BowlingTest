package com.jpn.bowling.exceptions;

import com.jpn.bowling.components.impl.BowlingGame;
import com.jpn.bowling.domain.Round;

public class InvalidScoreException extends RoundException {

	public InvalidScoreException(Round round, String score) {
		super(round,
				"Score (" + score + ") in round " + round.getNumber() + " it's invalid. Valid values are between 0-"
						+ BowlingGame.maxShotValue + " and letter F to indicate Fault shoot)");
	}
}
