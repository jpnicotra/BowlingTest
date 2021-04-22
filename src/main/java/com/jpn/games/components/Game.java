package com.jpn.games.components;

import java.util.List;

import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.domain.UserInput;
import com.jpn.games.exceptions.GameException;

/**
 * Generic interface for every kind of really basic game
 * 
 * @author jnicotra
 * @see Game
 */
public interface Game {

	/**
	 * Main method that generates a new game with an specific UserInput
	 * 
	 * @param userInput User input scores
	 * @return List of PlayerInfo
	 * @see UserInput
	 * @see PlayerInfo
	 */
	public List<PlayerInfo> newGame(UserInput userInput) throws GameException;

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
	public String formatGame(List<PlayerInfo> players, PlayerInfoFormatter playerInfoFormatter);
}
