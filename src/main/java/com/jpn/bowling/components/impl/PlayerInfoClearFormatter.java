package com.jpn.bowling.components.impl;

import java.util.List;

import com.jpn.bowling.components.BowlingPlayerInfoFormatter;
import com.jpn.games.domain.PlayerInfo;

public class PlayerInfoClearFormatter extends BowlingPlayerInfoFormatter {

	public String formatPlayersInfo(List<PlayerInfo> players) {
		final String enter="\r\n";
		final String stringRounds = getFramesHeader("\t");
		StringBuffer sb = new StringBuffer();
		players.stream().forEach(player -> {
			sb.append("Round\t"+stringRounds+enter);
			sb.append(player.getPlayerName()+enter);
			if (player.getErrorMessage()!=null) {
				sb.append("Exception found! "+player.getErrorMessage()+enter);
			}
			else {
				final String stringRound = player.getRounds().stream().map(round -> round.toString()).reduce((str, round) -> str+="\t"+round).get();
				sb.append("Pinfalls\t"+stringRound+enter);
				sb.append("Score\t");
				player.getRounds().forEach(round -> sb.append("\t"+round.getAccumulatedScore()));
				sb.append(""+enter);
			}
			sb.append("---------------------------------------------------------------------------------------------------------------------------"+enter);
		});

		return sb.toString();
	}
}
