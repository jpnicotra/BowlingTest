package com.jpn.bowling.components.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RoundInfoFormatter;
import com.jpn.bowling.domain.Round;

@Component
public class RoundRequestedFormatter implements RoundInfoFormatter {

	public String formatRoundInfo(Round round) {
		StringBuffer sb = new StringBuffer();
		final List<String> scores = round.getScores();
		
		if (scores.size()==1) {
			return "\t\t"+scores.get(0); 
		}
		else {
			for (String score : scores) {
				sb.append("\t"+score);
			}
		}

		return sb.toString();
	}
}
