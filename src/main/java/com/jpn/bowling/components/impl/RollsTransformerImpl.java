package com.jpn.bowling.components.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RollsTransformer;
import com.jpn.bowling.components.RoundsBuilder;
import com.jpn.bowling.domain.BowlingPlayerInfo;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

/**
 * Implementation of RollsTransformer to transform a map of each user with a
 * list of string rolls to a List of PlayerInfo
 * 
 * @author jnicotra
 * @see PlayerInfo
 */
@Component
public class RollsTransformerImpl implements RollsTransformer {

	@Autowired
	private RoundsBuilder roundsBuilder;

	/**
	 * Implementation of RollsTransformer to transform a map of each user with a
	 * list of string rolls to a List of PlayerInfo
	 * 
	 * @param rolls A map of each user with a list of string rolls
	 * @return List of PlayerInfo
	 * @see PlayerInfo
	 * @throws GameException If the process found anything unusual return this
	 *                       exception or any specific subclass of this exception
	 */
	public List<PlayerInfo> transformRollsToPlayerInfo(Map<String, List<String>> rolls) throws GameException {
		final List<PlayerInfo> players = rolls.entrySet().stream()
				.map(e -> new BowlingPlayerInfo(e.getKey(), e.getValue())).collect(Collectors.toList());

		players.stream().forEach(genericPlayer -> {
			BowlingPlayerInfo player = (BowlingPlayerInfo) genericPlayer;
			player.setRounds(roundsBuilder.buildRounds(player));
		});

		return players;
	}
}
