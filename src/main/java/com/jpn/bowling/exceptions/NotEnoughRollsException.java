package com.jpn.bowling.exceptions;

import com.jpn.games.exceptions.GameException;

public class NotEnoughRollsException extends GameException {

	public NotEnoughRollsException () {
		super("This game doesn't have enough shoots.");
	}
}
