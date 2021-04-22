package com.jpn.bowling.components;

import java.util.List;
import java.util.Map;

import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

/**
 * Interface that define specification to transform a map of each user with a
 * list of string rolls to a List of PlayerInfo
 * 
 * @author jnicotra
 * @see PlayerInfo
 */
public interface RollsTransformer {

	/**
	 * Interface that define specification to transform a map of each user with a
	 * list of string rolls to a List of PlayerInfo
	 * 
	 * @param rolls A map of each user with a list of string rolls
	 * @return List of PlayerInfo
	 * @see PlayerInfo
	 * @throws GameException If the process found anything unusual return this
	 *                       exception or any specific subclass of this exception
	 */
	public List<PlayerInfo> transformRollsToPlayerInfo(Map<String, List<String>> rolls) throws GameException;
}
