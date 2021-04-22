package com.jpn.bowling.components.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jpn.bowling.components.BowlingPlayerInfoFormatter;
import com.jpn.bowling.components.RoundInfoFormatter;
import com.jpn.bowling.domain.BowlingPlayerInfo;
import com.jpn.games.domain.PlayerInfo;

/**
 * Requested formatter for a tab splits look and feel
 */
public class PlayerInfoRequestedFormatter extends BowlingPlayerInfoFormatter {

	@Autowired
	private RoundInfoFormatter roundRequestedFormatter;

	/**
	 * Requested formatter for a tab splits look and feel
	 * 
	 * @param players List of playersInfo
	 * @return String String representation of each player
	 */
	public String formatPlayersInfo(List<PlayerInfo> players) {
		final String enter = "\r\n";
		final String stringRounds = getFramesHeader("\t\t");

		StringBuffer sb = new StringBuffer();
		players.stream().filter(player -> player instanceof BowlingPlayerInfo).forEach(genericPlayer -> {
			BowlingPlayerInfo player = (BowlingPlayerInfo) genericPlayer;
			sb.append("Frame" + stringRounds + enter);
			sb.append(player.getPlayerName() + enter);
			if (player.getErrorMessage() != null) {
				sb.append("Exception found! " + player.getErrorMessage() + enter);
			} else {
				final String stringRound = player.getRounds().stream()
						.map(round -> roundRequestedFormatter.formatRoundInfo(round))
						.reduce((str, round) -> str += round).get();
				sb.append("Pinfalls" + stringRound + enter);
				sb.append("Score");
				player.getRounds().forEach(round -> sb.append("\t\t" + round.getAccumulatedScore()));
				sb.append("" + enter);
			}
		});

		return sb.toString();
	}
}
