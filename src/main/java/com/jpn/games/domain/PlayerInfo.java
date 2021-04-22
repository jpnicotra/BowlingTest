package com.jpn.games.domain;

import java.util.List;

import com.jpn.bowling.domain.Round;

public abstract interface PlayerInfo {
	
	public String getPlayerName();
	public java.util.List<Round> getRounds();
	public void setRounds(java.util.List<Round> round);
	public String getErrorMessage();
	public String setErrorMessage(String error);
	public List<String> getScores();
	public int getFinalScore();

}
