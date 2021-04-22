package com.jpn.games.components;

import com.jpn.games.domain.GenericRound;
import com.jpn.games.domain.PlayerInfo;

/**
 * Generic interface used to calculate final score for an specific player in an
 * specific round
 * 
 * @author jnicotra
 */
public interface PlayerScoreCalculator {

	/**
	 * Generic interface used to calculate final score for an specific player in an
	 * specific round
	 * 
	 * @param player PlayerInfo
	 * @param round  GenericRound
	 * @return Final score
	 */
	public int calculateScore(PlayerInfo player, GenericRound round);
}
