package com.jpn.bowling.domain;

import java.util.ArrayList;
import java.util.List;

import com.jpn.bowling.components.impl.BowlingGame;
import com.jpn.games.domain.GenericRound;

/**
 * Represents a Bowling specific Round or Frame
 * 
 * @author jnicotra
 */
public class Round extends GenericRound {
	private Integer finalScore;
	private Integer accumulatedScore;
	private List<Integer> points;
	private boolean full;
	private boolean lastRound;

	private RoundType roundType = RoundType.NOT_DEFINED;

	/**
	 * Inititalize Round with number specified by parameter
	 * 
	 * @param roundNumber Round number
	 */
	public Round(int roundNumber) {
		super(roundNumber);
		this.finalScore = null;
		this.accumulatedScore = 0;
		points = new ArrayList<Integer>();
		full = false;
		lastRound = (roundNumber == BowlingGame.numberOfRounds);
	}

	public int getSumOfPoints() {
		return points.stream().reduce(0, Integer::sum);
	}
	/*
	 * private int getScoreFromSimpleRound() throws GameException { int newScore =
	 * getSumOfPoints();
	 * 
	 * if (!lastRound && newScore > BowlingGame.maxShotValue) { throw new
	 * ScoreExceedsAllowedValueException(this, "Score " + newScore + " in round " +
	 * getNumber() + " exceeds maximum allowed value " + BowlingGame.maxShotValue);
	 * }
	 * 
	 * // Checking if last round has an invalid combination of values if
	 * (points.size() == 2 && points.get(0) < BowlingGame.maxShotValue && newScore >
	 * BowlingGame.maxShotValue) { throw new ScoreExceedsAllowedValueException(this,
	 * "Score " + newScore + " in round " + getNumber() +
	 * " exceeds maximum allowed value " + BowlingGame.maxShotValue); }
	 * 
	 * return newScore;
	 * 
	 * }
	 */
	/**
	 * Add additional bowling score in string format
	 * 
	 * @throws GameException In case score does not fit expected values
	 */

	/*
	 * public void addScore(String score) throws GameException { int point = 0; //
	 * User can send F and represents 0 pins down switch (score) { case
	 * BowlingGame.FAULT: point = 0; break; default: point =
	 * Integer.parseInt(score); break; } if (point > BowlingGame.maxShotValue ||
	 * point < 0) { throw new ScoreOutOfRangeException(this, score); } // Add
	 * numeric point to the list points.add(point); // Get String representation of
	 * this score (number, X, / or F) score =
	 * bowlingScoreFormatter.getStringScore(this, score);
	 * 
	 * // TODO move validations outside int maxShoots = (lastRound ?
	 * BowlingGame.maxShotsLastRound : BowlingGame.maxShotsPerRound); final int
	 * pointsSize = points.size(); if (pointsSize > maxShoots) throw new
	 * ExceedMaximumNumberOfRollsException(this, "User can't do more than " +
	 * maxShoots + " rolls in round " + getNumber());
	 * 
	 * finalScore = getScoreFromSimpleRound();
	 * 
	 * if (!lastRound) { if (pointsSize == 1 && finalScore ==
	 * BowlingGame.maxShotValue) { roundType = RoundType.STRIKE; } if (pointsSize ==
	 * BowlingGame.maxShotsPerRound && finalScore == BowlingGame.maxShotValue) {
	 * roundType = RoundType.SPARE; } if (pointsSize == BowlingGame.maxShotsPerRound
	 * && finalScore < BowlingGame.maxShotValue) roundType = RoundType.SIMPLE;
	 * 
	 * if (roundType != RoundType.NOT_DEFINED) { full = true; } }
	 * 
	 * if (lastRound && pointsSize == BowlingGame.maxShotsLastRound) { if
	 * (getScores().get(1)!= BowlingGame.STRIKE && getScores().get(1)!=
	 * BowlingGame.SPARE) { if (point==BowlingGame.maxShotValue) throw new
	 * ScoreExceedsAllowedValueException(this, score); } }
	 * 
	 * getScores().add(score); }
	 */
	/**
	 * Returns true if this round is complete or false in case user still have more
	 * shoots. return True if this round is complete
	 */
	public boolean isFull() {
		return full;
	}

	/**
	 * Returns final score for this round
	 * 
	 * @return Final score
	 */
	public Integer getFinalScore() {
		return finalScore;
	}

	/**
	 * Returns list of scores of this round in integer format (Fault for example
	 * moves to 0 or X to 10)
	 * 
	 * @return List of scores in integer format
	 */
	public List<Integer> getPoints() {
		return points;
	}

	@Override
	public String toString() {
		if (getScores().size() > 0)
			return getScores().stream().reduce((s, v) -> s += " " + v).get();
		return "";
	}

	/**
	 * Returns a sum of this round and prior rounds scores
	 * 
	 * @return Total Score
	 */
	public Integer getAccumulatedScore() {
		return accumulatedScore;
	}

	/**
	 * Set the accumulated score of this round
	 * 
	 * @param accumulatedScore Total Score
	 */
	public void setAccumulatedScore(Integer accumulatedScore) {
		this.accumulatedScore = accumulatedScore;
	}

	/**
	 * @return Round type
	 * @see RoundType
	 */
	public RoundType getRoundType() {
		return roundType;
	}

	/**
	 * @return true if this is last round
	 */
	public boolean isLastRound() {
		return lastRound;
	}

	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}

	public void setRoundType(RoundType roundType) {
		this.roundType = roundType;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

}
