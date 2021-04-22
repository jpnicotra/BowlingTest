package com.jpn.bowling.exceptions;

import java.nio.file.NoSuchFileException;

import com.jpn.games.exceptions.GameException;

public class NoSuchFileGameException extends GameException {

	public NoSuchFileGameException (NoSuchFileException fileException) {
		super(fileException.toString());
	}
}
