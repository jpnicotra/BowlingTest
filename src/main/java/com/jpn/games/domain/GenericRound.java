package com.jpn.games.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a specific Round in a game
 * 
 * @author jnicotra
 */
public abstract class GenericRound {
	private int number;
	private List<String> scores;

	public GenericRound(int roundNumber) {
		this.number = roundNumber;
		scores = new ArrayList<String>();
	}

	public abstract boolean isFull();

	public int getNumber() {
		return number;
	}

	public List<String> getScores() {
		return scores;
	}

}
