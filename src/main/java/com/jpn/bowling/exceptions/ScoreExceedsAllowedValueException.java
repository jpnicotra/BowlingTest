package com.jpn.bowling.exceptions;

import com.jpn.bowling.domain.Round;

public class ScoreExceedsAllowedValueException extends RoundException {
	
	public ScoreExceedsAllowedValueException (Round round, String message) {
		super(round, message);
	}
}
