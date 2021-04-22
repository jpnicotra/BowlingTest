package com.jpn.bowling.components.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RollsTransformer;
import com.jpn.bowling.exceptions.NoMovesException;
import com.jpn.games.components.Game;
import com.jpn.games.components.PlayerInfoFormatter;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.domain.UserInput;
import com.jpn.games.exceptions.GameException;

/**
 * Main class of the Bowling Game. This class implements Game interface and
 * holds information about the basic Constants required for this kind of game
 * 
 * @author jnicotra
 * @see Game
 */
@Component
public class BowlingGame implements Game {
	private static final Logger LOGGER = LogManager.getLogger(BowlingGame.class);
	/**
	 * Number of frames in each game
	 */
	public static final int numberOfRounds = 10;

	/**
	 * String representation of a missed shot
	 */
	public static final String FAULT = "F";
	/**
	 * String representation of a Strike shoot
	 */
	public static final String STRIKE = "X";
	/**
	 * String representation of a Spare shoot
	 */
	public static final String SPARE = "/";
	/**
	 * Maximum number of pins that could be downed
	 */
	public static final int maxShotValue = 10;
	/**
	 * Maximum number of shots per common frame
	 */
	public static final int maxShotsPerRound = 2;
	/**
	 * Maximum number of shots in the last frame
	 */
	public static final int maxShotsLastRound = 3;

	@Autowired
	private RollsTransformer rollsToPlayerInfoTransformer;

	public BowlingGame() {
	}

	/**
	 * Main method that generates a new game with an specific UserInput
	 * 
	 * @param userInput User input scores
	 * @return List of PlayerInfo
	 * @see UserInput
	 * @see PlayerInfo
	 */
	public List<PlayerInfo> newGame(UserInput userInput) throws GameException {
		if (userInput == null)
			throw new NoMovesException();
		// Get all rolls send by the user
		final Map<String, List<String>> rolls = userInput.getRolls();
		final List<PlayerInfo> players = rollsToPlayerInfoTransformer.transformRollsToPlayerInfo(rolls);

		return players;
	}

	/**
	 * This method receives a list of player info with all rounds and results and
	 * apply an specific formatter to return a String representation
	 * 
	 * @return String representation of the game
	 * @param players             List of PlayerInfo
	 * @param playerInfoFormatter Specific formatter to apply
	 * @see PlayerInfo
	 * @see PlayerInfoFormatter
	 */
	public String formatGame(List<PlayerInfo> players, PlayerInfoFormatter playerInfoFormatter) {
		return playerInfoFormatter.formatPlayersInfo(players);
	}
}
