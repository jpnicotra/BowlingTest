package com.jpn.games.domain;

import java.util.List;

/**
 * Generic information for a game player
 * 
 * @author jnicotra
 */
public abstract class PlayerInfo {
	private String playerName;
	private String errorMessage;

	public PlayerInfo(String name) {
		this.playerName = name;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String setErrorMessage(String error) {
		return this.errorMessage = error;
	}

	public abstract List<String> getScores();

	public abstract int getFinalScore();

}
