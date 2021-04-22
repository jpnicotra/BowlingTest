package com.jpn.bowling.components;

import java.util.List;
import java.util.Map;

import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

public interface RollsTransformer {

	public List<PlayerInfo> transformRollsToPlayerInfo(Map<String, List<String>> rolls) throws GameException;
}
