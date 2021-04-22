package com.jpn.bowling.components.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RoundInfoFormatter;
import com.jpn.bowling.domain.Round;

@Component
public class RoundClearFormatter implements RoundInfoFormatter {

	public String formatRoundInfo(Round round) {
		final List<String> scores = round.getScores();
		if (scores.size()>0)
			return scores.stream().reduce((s,v) -> s+=" "+v).get();

		return "";
	}
}
