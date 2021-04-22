package com.jpn.bowling.components;

import com.jpn.bowling.domain.Round;

/**
 * Interface that define action to return an string representation of a Round
 * 
 * @author jnicotra
 * @see Round
 */
public interface RoundInfoFormatter {

	/**
	 * Interface that define action to return an string representation of a Round
	 * 
	 * @param round The round you want to represent
	 * @return string representation of a Round
	 * @see Round
	 */
	public String formatRoundInfo(Round round);
}
