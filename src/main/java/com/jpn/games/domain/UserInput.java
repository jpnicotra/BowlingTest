package com.jpn.games.domain;

import java.util.List;
import java.util.Map;

/**
 * Generic interface with basic methods needed to read user inputs of a game
 * 
 * @author jnicotra
 *
 */
public abstract interface UserInput {

	public void readInputs(String instructions);

	public Map<String, List<String>> getRolls();

}
