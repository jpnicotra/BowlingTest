package com.jpn.bowling.exceptions;

import com.jpn.games.exceptions.GameException;

public class NoMovesException extends GameException {

	public NoMovesException() {
		super("User input can not be null");
	}
}
