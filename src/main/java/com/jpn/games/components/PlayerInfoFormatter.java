package com.jpn.games.components;

import java.util.List;

import com.jpn.games.domain.PlayerInfo;

/**
 * This interface defines the action required to return a string representation
 * of a list of players
 * 
 * @author jnicotra
 * @see PlayerInfoFormatter
 */
public interface PlayerInfoFormatter {

	/**
	 * Custom formatter that represents all information about a list of players in a
	 * game
	 * 
	 * @param players List of playersInfo
	 * @return String String representation of each player
	 */
	public String formatPlayersInfo(List<PlayerInfo> players);
}
