package com.jpn.bowling.components;

import java.util.List;

import com.jpn.bowling.domain.BowlingPlayerInfo;
import com.jpn.bowling.domain.Round;
import com.jpn.games.domain.PlayerInfo;

/**
 * Defines how scores sent by the users are transformed in Rounds and returns
 * new list of rounds
 * 
 * @author jnicotra
 * @see Round
 * @see PlayerInfo
 */
public interface RoundsBuilder {

	/**
	 * Defines how scores sent by the users are transformed in Rounds and returns
	 * new list of rounds
	 * 
	 * @param player Player that holds all string scores
	 * @return List of Rounds
	 * @see Round
	 * @see PlayerInfo
	 */
	public List<Round> buildRounds(BowlingPlayerInfo player);
}
