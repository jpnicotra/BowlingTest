package com.jpn.games.exceptions;

public abstract class GameException extends RuntimeException {

	public GameException(String errorMessage) {
		super(errorMessage);
	}

}
