package com.jpn.bowling.components.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RollsTransformer;
import com.jpn.bowling.exceptions.NoMovesException;
import com.jpn.bowling.input.UserInput;
import com.jpn.games.components.Game;
import com.jpn.games.components.PlayerInfoFormatter;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

@Component
public class BowlingGame implements Game {
	private static final Logger LOGGER = LogManager.getLogger(BowlingGame.class);
	public static final int numberOfRounds=10;
	public static final String FAULT = "F";
	public static final String STRIKE = "X";
	public static final String SPARE = "/";
	public static final int maxShotValue = 10;
	public static final int maxShotsPerRound = 2;
	public static final int maxShotsLastRound = 3;
	
	@Autowired
	private RollsTransformer rollsToPlayerInfoTransformer;
	
	public BowlingGame() {
	}

	public List<PlayerInfo> newGame(UserInput userInput) throws GameException {
		if (userInput == null)
			throw new NoMovesException();
		// Get all rolls send by the user
		final Map<String, List<String>> rolls = userInput.getRolls();
		final List<PlayerInfo> players = rollsToPlayerInfoTransformer.transformRollsToPlayerInfo(rolls);
		
		return players;
	}
	
	public String formatGame (List<PlayerInfo> players, PlayerInfoFormatter playerInfoFormatter) {
		return playerInfoFormatter.formatPlayersInfo(players);
	}
}
