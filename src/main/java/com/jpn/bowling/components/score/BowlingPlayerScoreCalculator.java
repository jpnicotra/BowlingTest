package com.jpn.bowling.components.score;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpn.bowling.domain.BowlingPlayerInfo;
import com.jpn.bowling.domain.Round;
import com.jpn.bowling.domain.RoundType;
import com.jpn.games.components.PlayerScoreCalculator;
import com.jpn.games.domain.GenericRound;
import com.jpn.games.domain.PlayerInfo;

/**
 * Specific bowling implementation of PlayerScoreCalculator used to calculate
 * final score for an specific player in an specific round of bowling
 * 
 * @author jnicotra
 */
@Component
public class BowlingPlayerScoreCalculator implements PlayerScoreCalculator {

	@Autowired
	private RoundScoreCalculator roundSimpleScoreCalculator;
	@Autowired
	private RoundScoreCalculator roundSpareScoreCalculator;
	@Autowired
	private RoundScoreCalculator roundStrikeScoreCalculator;

	/**
	 * Specific bowling implementation of PlayerScoreCalculator used to calculate
	 * final score for an specific player in an specific round of bowling
	 * 
	 * @author jnicotra
	 * @param genericPlayer PlayerInfo
	 * @param genericRound  GenericRound
	 * @return Final score
	 */
	@Override
	public int calculateScore(PlayerInfo genericPlayer, GenericRound genericRound) {
		int score = 0;
		if (genericRound instanceof Round && genericPlayer instanceof BowlingPlayerInfo) {
			BowlingPlayerInfo player = (BowlingPlayerInfo) genericPlayer;
			Round round = (Round) genericRound;

			final List<Round> rounds = player.getRounds();
			final int index = round.getNumber() - 1;

			// gets sum score until this round
			if (index > 0) {
				score += roundSimpleScoreCalculator.calculate(rounds, index, round);
			}

			if (round.getRoundType() == RoundType.STRIKE) {
				score += roundStrikeScoreCalculator.calculate(rounds, index, round);
			}
			if (round.getRoundType() == RoundType.SPARE) {
				score += roundSpareScoreCalculator.calculate(rounds, index, round);
			}
			
			score += round.getFinalScore();
		}

		return score;
	}

}
