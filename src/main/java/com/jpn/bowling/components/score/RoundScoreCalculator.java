package com.jpn.bowling.components.score;

import com.jpn.bowling.domain.Round;

public interface RoundScoreCalculator {

	public int calculate(java.util.List<Round> rounds, int index, Round round);
}
