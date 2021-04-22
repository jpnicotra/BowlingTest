package com.jpn.games.components;

import com.jpn.bowling.domain.Round;
import com.jpn.games.domain.PlayerInfo;

public interface PlayerScoreCalculator {

	public int calculateScore(PlayerInfo player, Round round);
}
