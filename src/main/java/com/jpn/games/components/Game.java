package com.jpn.games.components;

import java.util.List;

import com.jpn.bowling.input.UserInput;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

public interface Game {
	public List<PlayerInfo> newGame(UserInput userInput) throws GameException;
}
