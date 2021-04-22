package com.jpn.bowling.input;

import java.util.List;
import java.util.Map;


public abstract interface UserInput {
	
	public void readInputs (String instructions);
	public Map<String, List<String>> getRolls();

}
