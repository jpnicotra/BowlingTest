package com.jpn.bowling.exceptions;

import com.jpn.bowling.domain.Round;

public class ExceedMaximumNumberOfRollsException extends RoundException {

	public ExceedMaximumNumberOfRollsException(Round round, String message) {
		super(round, message);
	}
}
