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

@Component
public class RollsTransformerImpl implements RollsTransformer {

	@Autowired
	private RoundsBuilder roundsBuilder;

	public List<PlayerInfo> transformRollsToPlayerInfo(Map<String, List<String>> rolls) throws GameException {
		final List<PlayerInfo> players = rolls.entrySet().stream().map(e -> new BowlingPlayerInfo(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		players.stream().forEach(player -> roundsBuilder.buildRounds(player));

		return players;
	}
}
