package com.jpn.bowling.components.score;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jpn.bowling.components.impl.BowlingGame;
import com.jpn.bowling.domain.Round;
import com.jpn.bowling.domain.RoundType;
import com.jpn.bowling.exceptions.ExceedMaximumNumberOfRollsException;
import com.jpn.bowling.exceptions.InvalidScoreException;
import com.jpn.bowling.exceptions.ScoreExceedsAllowedValueException;
import com.jpn.bowling.exceptions.ScoreOutOfRangeException;
import com.jpn.games.exceptions.GameException;

/**
 * Defines how scores are pre-validated and added to the round
 * 
 * @author jnicotra
 */
@Component
public class BowlingScoreController implements ScoreController {

	/**
	 * Defines how scores are transformed to string format for Bowling board
	 * 
	 * @param score Score
	 * @return New string representation
	 */
	private String getStringScore(Round round, String score) {
		final List<Integer> points = round.getPoints();
		int currentScore = points.stream().reduce(0, Integer::sum);

		if (!round.isLastRound()) {
			if (currentScore == BowlingGame.maxShotValue) {
				// Max score reached in only one shot = Strike!
				if (points.size() == 1) {
					return BowlingGame.STRIKE;
				}
				// Max score reached in MORE than one shot = Spare!
				else {
					return BowlingGame.SPARE;
				}
			}
		} else {
			// If every roll was perfect then it's another STRIKE!
			if (currentScore == (BowlingGame.maxShotValue * points.size())) {
				return BowlingGame.STRIKE;
			} else {
				if (points.size() == 3 && score.equals("" + BowlingGame.maxShotValue)) {
					return BowlingGame.STRIKE;
				}
				if (points.size() == 2 && currentScore == BowlingGame.maxShotValue) {
					return BowlingGame.SPARE;
				}
			}
		}

		return score;
	}

	/**
	 * Add additional bowling score in string format
	 * 
	 * @throws GameException In case score does not fit expected values
	 */
	public void addScore(Round round, String score) throws GameException {
		final boolean lastRound = round.isLastRound();
		List<Integer> points = round.getPoints();
		int point = 0;
		// User can send F and represents 0 pins down
		switch (score) {
		case BowlingGame.FAULT:
			point = 0;
			break;
		default:
			try {
				point = Integer.parseInt(score);
			}
			catch (Exception e) {
				throw new InvalidScoreException(round, score);
			}
			break;
		}
		if (point > BowlingGame.maxShotValue || point < 0) {
			throw new ScoreOutOfRangeException(round, score);
		}
		// Add numeric point to the list
		points.add(point);

		// Get String representation of this score (number, X, / or F)
		score = getStringScore(round, score);

		int maxShoots = (lastRound ? BowlingGame.maxShotsLastRound : BowlingGame.maxShotsPerRound);
		final int pointsSize = points.size();
		if (pointsSize > maxShoots)
			throw new ExceedMaximumNumberOfRollsException(round,
					"User can't do more than " + maxShoots + " rolls in round " + round.getNumber());

		final Integer finalScore = getScoreFromSimpleRound(round);
		round.setFinalScore(finalScore);

		if (!lastRound) {
			if (pointsSize == 1 && finalScore == BowlingGame.maxShotValue) {
				round.setRoundType(RoundType.STRIKE);
			}
			if (pointsSize == BowlingGame.maxShotsPerRound && finalScore == BowlingGame.maxShotValue) {
				round.setRoundType(RoundType.SPARE);
			}
			if (pointsSize == BowlingGame.maxShotsPerRound && finalScore < BowlingGame.maxShotValue)
				round.setRoundType(RoundType.SIMPLE);

			if (round.getRoundType() != RoundType.NOT_DEFINED) {
				round.setFull(true);
			}
		}

		if (lastRound && pointsSize == BowlingGame.maxShotsLastRound) {
			if (round.getScores().get(1) != BowlingGame.STRIKE && round.getScores().get(1) != BowlingGame.SPARE) {
				if (point == BowlingGame.maxShotValue)
					throw new ScoreExceedsAllowedValueException(round, score);
			}
		}

		round.getScores().add(score);
	}

	private int getScoreFromSimpleRound(Round round) throws GameException {
		int newScore = round.getSumOfPoints();

		if (!round.isLastRound() && newScore > BowlingGame.maxShotValue) {
			throw new ScoreExceedsAllowedValueException(round, "Score " + newScore + " in round " + round.getNumber()
					+ " exceeds maximum allowed value " + BowlingGame.maxShotValue);
		}

		// Checking if last round has an invalid combination of values
		if (round.getPoints().size() == 2 && round.getPoints().get(0) < BowlingGame.maxShotValue
				&& newScore > BowlingGame.maxShotValue) {
			throw new ScoreExceedsAllowedValueException(round, "Score " + newScore + " in round " + round.getNumber()
					+ " exceeds maximum allowed value " + BowlingGame.maxShotValue);
		}

		return newScore;

	}

}
