package com.jpn.bowling.domain;

import java.util.ArrayList;
import java.util.List;

import com.jpn.bowling.components.impl.BowlingGame;
import com.jpn.bowling.exceptions.ExceedMaximumNumberOfRollsException;
import com.jpn.bowling.exceptions.ScoreExceedsAllowedValueException;
import com.jpn.bowling.exceptions.ScoreOutOfRangeException;
import com.jpn.games.exceptions.GameException;

public class Round {
	public enum RoundType {
		NOT_DEFINED, SIMPLE, SPARE, STRIKE
		
	}
	private int number;
	private Integer finalScore;
	private Integer accumulatedScore;
	private List<String> scores;
	private List<Integer> points;
	private boolean full;
	private boolean lastRound;
	
	private RoundType roundType=RoundType.NOT_DEFINED;

	public Round(int roundNumber) {
		this.number = roundNumber;
		this.finalScore = null;
		this.accumulatedScore = 0;
		scores = new ArrayList<String>();
		points = new ArrayList<Integer>();
		full = false;
		lastRound = (roundNumber == BowlingGame.numberOfRounds);
	}
	
	private String getStringScore(String score) {
		int currentScore = points.stream().reduce(0, Integer::sum);
		
		if (!lastRound) {
			if (currentScore==BowlingGame.maxShotValue) {
				// Max score reached in only one shot = Strike!
				if (points.size()==1) {
//					roundType = RoundType.STRIKE;
					return BowlingGame.STRIKE;
				}
				// Max score reached in MORE than one shot = Spare!
				else {
					//					roundType = RoundType.SPARE;
					return BowlingGame.SPARE;
				}
			}
		}
		else {
			// If every roll was perfect then it's another STRIKE!
			if (currentScore==(BowlingGame.maxShotValue*points.size())) {
				//roundType = RoundType.STRIKE;
				return BowlingGame.STRIKE;
			}
			else {
				// TODO CHECK LOGIC
				if (points.size()==3 && score.equals(""+BowlingGame.maxShotValue)) {
					//roundType = RoundType.STRIKE;
					return BowlingGame.STRIKE;
				}
				if (points.size()==2 && currentScore==BowlingGame.maxShotValue) {
					//roundType = RoundType.SPARE;
					return BowlingGame.SPARE;
				}
			}
		}
		
		return score;
	}
	
	private int getSumOfPoints() {
		return points.stream().reduce(0, Integer::sum);
	}
	
	private int getScoreFromSimpleRound() throws GameException {
		int newScore = getSumOfPoints();
		
		if (!lastRound && newScore>BowlingGame.maxShotValue) {
			throw new ScoreExceedsAllowedValueException (this, "Score "+newScore+" in round "+number+" exceeds maximum allowed value "+BowlingGame.maxShotValue);
		}

		// Checking if last round has an invalid combination of values
		if (points.size()==2 && points.get(0)<BowlingGame.maxShotValue && newScore>BowlingGame.maxShotValue) {
			throw new ScoreExceedsAllowedValueException (this, "Score "+newScore+" in round "+number+" exceeds maximum allowed value "+BowlingGame.maxShotValue);
		}
		
		return newScore;

	}

	public void addScore(String score) throws GameException {
		// TODO CAMBIAR EL LUGAR DEL CÁLCULO
		int point = 0;
		// User can send F and represents 0 pins down
		switch (score) {
			case BowlingGame.FAULT:
				point = 0;
				break;
			default:
				point = Integer.parseInt(score);
				break;
		}
		if (point>BowlingGame.maxShotValue || point < 0) {
			throw new ScoreOutOfRangeException(this, score);
		}
		// Add numeric point to the list
		points.add(point);
		// Get String representation of this score (number, X, / or F)
		score = getStringScore(score);
		
		// TODO move validations outside
		int maxShoots = (lastRound ? BowlingGame.maxShotsLastRound : BowlingGame.maxShotsPerRound);
		final int pointsSize = points.size();
		if (pointsSize>maxShoots)
			throw new ExceedMaximumNumberOfRollsException (this, "User can't do more than "+maxShoots+" rolls in round "+number);

		finalScore = getScoreFromSimpleRound();
		
		if (!lastRound) {
			if (pointsSize==1 && finalScore==BowlingGame.maxShotValue) {
				roundType = RoundType.STRIKE;
			}
			if (pointsSize==BowlingGame.maxShotsPerRound && finalScore==BowlingGame.maxShotValue) {
				roundType = RoundType.SPARE;
			}
			if (pointsSize==BowlingGame.maxShotsPerRound && finalScore<BowlingGame.maxShotValue)
				roundType = RoundType.SIMPLE;
			
			if (roundType!=RoundType.NOT_DEFINED) {
				full = true;
			}
		}

		scores.add(score);
	}

	public boolean isFull() {
		return full;
	}

	public int getNumber() {
		return number;
	}

	public Integer getFinalScore() {
		return finalScore;
	}

	public List<String> getScores() {
		return scores;
	}

	public List<Integer> getPoints() {
		return points;
	}

	@Override
	public String toString() {
		if (scores.size()>0)
			return scores.stream().reduce((s,v) -> s+=" "+v).get();
		return "";
	}

	public Integer getAccumulatedScore() {
		return accumulatedScore;
	}

	public void setAccumulatedScore(Integer accumulatedScore) {
		this.accumulatedScore = accumulatedScore;
	}

	public RoundType getRoundType() {
		return roundType;
	}

	public boolean isLastRound() {
		return lastRound;
	}

}
