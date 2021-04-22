package com.jpn.bowling.components;

import com.jpn.bowling.components.impl.BowlingGame;
import com.jpn.games.components.PlayerInfoFormatter;

public abstract class BowlingPlayerInfoFormatter implements PlayerInfoFormatter {

	public String getFramesHeader(String split) {
		String strRounds = "";
		for (int i=1;i<=BowlingGame.numberOfRounds;i++)
			strRounds+=split+i;
		
		return strRounds;
	}
}
