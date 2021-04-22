package com.jpn.bowling.components.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RoundInfoFormatter;
import com.jpn.bowling.domain.Round;

/**
 * Implementation of interface RoundInfoFormatter with a human readable style of
 * a Round
 * 
 * @see Round
 * @author jnicotra
 */
@Component
public class RoundClearFormatter implements RoundInfoFormatter {

	/**
	 * Implementation of interface RoundInfoFormatter with a human readable style of
	 * a Round
	 * 
	 * @param round The round you want to represent
	 * @return string representation of a Round
	 * @see Round
	 */
	public String formatRoundInfo(Round round) {
		StringBuffer sb = new StringBuffer();
		final List<String> scores = round.getScores();
		for (String score : scores) {
			sb.append(score + " ");
		}

		return "\t" + sb.toString().trim();
	}
}
