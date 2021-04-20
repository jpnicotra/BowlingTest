package com.jpn.bowling.input;

import java.util.List;
import java.util.Map;

import com.jpn.bowling.domain.PlayerChance;

public abstract interface UserInput {
	
	public PlayerChance nextMove();
	public Map<String, List<String>> getRolls();

}
