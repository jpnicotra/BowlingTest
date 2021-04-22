package com.jpn.bowling.components;

import com.jpn.bowling.components.impl.BowlingGame;
import com.jpn.games.components.PlayerInfoFormatter;

/**
 * This abstract class implements PlayerInfoFormatter and add a method to return
 * a string representation of each frame
 * 
 * @author jnicotra
 * @see PlayerInfoFormatter
 */
public abstract class BowlingPlayerInfoFormatter implements PlayerInfoFormatter {

	/**
	 * Builds a string representation of each frame header
	 * 
	 * @param split Character used to split each frame
	 * @return string representation of each frame header
	 */
	public String getFramesHeader(String split) {
		String strRounds = "";
		for (int i = 1; i <= BowlingGame.numberOfRounds; i++)
			strRounds += split + i;

		return strRounds;
	}
}
