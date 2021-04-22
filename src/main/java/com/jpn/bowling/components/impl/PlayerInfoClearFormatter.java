package com.jpn.bowling.components.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jpn.bowling.components.BowlingPlayerInfoFormatter;
import com.jpn.bowling.components.RoundInfoFormatter;
import com.jpn.bowling.domain.BowlingPlayerInfo;
import com.jpn.bowling.domain.Round;
import com.jpn.games.domain.PlayerInfo;

/**
 * Custom formatter for a clean and more human readable look style instead of
 * requested style with tabs splits
 * 
 * @author jnicotra
 */
public class PlayerInfoClearFormatter extends BowlingPlayerInfoFormatter {

	@Autowired
	private RoundInfoFormatter roundClearFormatter;

	/**
	 * Custom formatter for a clean and more human readable look style instead of
	 * requested style with tabs splits
	 * 
	 * @param players List of playersInfo
	 * @return String String representation of each player
	 */
	public String formatPlayersInfo(List<PlayerInfo> players) {
		StringBuffer sb = new StringBuffer();
		final String enter = "\r\n";
		final String stringRounds = getFramesHeader("\t");
		players.stream().filter(player -> player instanceof BowlingPlayerInfo).forEach(genericPlayer -> {
			BowlingPlayerInfo player = (BowlingPlayerInfo) genericPlayer;
			sb.append("Round\t" + stringRounds + enter);
			sb.append(player.getPlayerName() + enter);
			if (player.getErrorMessage() != null) {
				sb.append("Exception found! " + player.getErrorMessage() + enter);
			} else {
//				final String stringRound = player.getRounds().stream().map(round -> round.toString()).reduce((str, round) -> str+="\t"+round).get();
				final String stringRound = player.getRounds().stream()
						.map(round -> roundClearFormatter.formatRoundInfo(round)).reduce((str, round) -> str += round)
						.get();
				sb.append("Pinfalls" + stringRound + enter);
				sb.append("Score\t");
				player.getRounds().forEach(round -> sb.append("\t" + round.getAccumulatedScore()));
				sb.append("" + enter);
			}
			sb.append(
					"---------------------------------------------------------------------------------------------------------------------------"
							+ enter);
		});

		return sb.toString();
	}
}
