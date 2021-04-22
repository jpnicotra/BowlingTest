package com.jpn.bowling.components.score;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jpn.bowling.domain.Round;
import com.jpn.bowling.domain.RoundType;

/**
 * Specific calculator for a strike round
 * 
 * @author jnicotra
 */
@Component
public class RoundStrikeScoreCalculator implements RoundScoreCalculator {

	/**
	 * Specific calculator for a strike round
	 * 
	 * @param rounds List of rounds
	 * @param index  Indicates round number in the list
	 * @param round  Bowling round
	 * @return int Final score of the round
	 */
	public int calculate(java.util.List<Round> rounds, int index, Round round) {
		int score = 0;

		if (round.getRoundType() == RoundType.STRIKE) {
			if (round.isLastRound()) {
				score += round.getPoints().get(round.getPoints().size() - 1);
			} else {
				// TODO CHECK SIZE
				// Get score of next round
				Round addRound = rounds.get(index + 1);
				final List<Integer> points = addRound.getPoints();
				int maxPoints = (addRound.isLastRound() ? 2 : points.size());
				java.util.List<Integer> addPoints = points.subList(0, (points.size() >= maxPoints ? maxPoints : 1));

				// If this guy is a really PRO and did another Strike, I have to get value from
				// next Round
				if (addPoints.size() < 2 && (index + 2) < rounds.size()) {
					addRound = rounds.get(index + 2);
					addPoints.add(addRound.getPoints().get(0));
				}
				// Add all points from next rounds
				for (Integer addScore : addPoints) {
					score += addScore;
				}
			}
		}

		return score;
	}
}
