package com.jpn.bowling.domain;

import java.util.List;

import com.jpn.bowling.components.impl.BowlingGame;
import com.jpn.bowling.domain.Round.RoundType;
import com.jpn.games.domain.PlayerInfo;

public class BowlingPlayerInfo implements PlayerInfo {
	private final String playerName;
	private final List<String> scores;
	private java.util.List<Round> rounds;
	private String errorMessage;
	
	public BowlingPlayerInfo (String playerName, List<String> scores) {
		this.playerName = playerName;
		this.scores = scores;
	}

	public List<String> getScores() {
		return scores;
	}
	
	public java.util.List<Round> getRounds() {
		return rounds;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String setErrorMessage(String error) {
		return this.errorMessage = error;
	}

	@Override
	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
		
	}
	
	public int getFinalScore() {
		int finalScore = 0;
		if (rounds.size()>0)
			finalScore = rounds.get(rounds.size()-1).getAccumulatedScore();
		
		return finalScore;
	}
}
