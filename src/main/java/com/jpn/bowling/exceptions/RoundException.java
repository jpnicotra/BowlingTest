package com.jpn.bowling.exceptions;

import com.jpn.bowling.domain.Round;
import com.jpn.games.exceptions.GameException;

public class RoundException extends GameException {
	private Round round;
	
	public RoundException (Round round, String message) {
		super(message);
		this.round = round;
	}

	public Round getRound() {
		return round;
	}
}
