package com.jpn.bowling.exceptions;

import com.jpn.games.exceptions.GameException;

public class IoGameException extends GameException {

	public IoGameException (Exception exception) {
		super(exception.toString());
	}
}
