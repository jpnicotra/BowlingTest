package com.jpn.bowling.domain;

import java.util.ArrayList;
import java.util.List;

import com.jpn.bowling.game.BowlingGame;

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

	public void addScore(String score) throws Exception {
		// TODO CAMBIAR EL LUGAR DEL CÁLCULO
		int point = 0;
		// User can send 10 pins down or X for Strikes or / for SPARE
		switch (score) {
			case BowlingGame.SPARE:
				point = BowlingGame.maxShotValue;
				break;
			case BowlingGame.STRIKE:
				point = BowlingGame.maxShotValue;
				break;
			case BowlingGame.FAULT:
				point = 0;
				break;
			default:
				point = Integer.parseInt(score);
				break;
		}
		
		if (point>BowlingGame.maxShotValue || point < 0) {
			throw new Exception ("Score ("+score+") in round "+number+" it's out of range (0-"+BowlingGame.maxShotValue+")");
		}
		points.add(point);
		finalScore = points.stream().reduce(0, Integer::sum);

		// TODO CHECK SUM FROM PIN FALLS
/*		if (finalScore>BowlingGame.maxShotValue || finalScore < 0) {
			throw new Exception ("Final score ("+finalScore+") in round "+this.number+" it's out of range (0-"+BowlingGame.maxShotValue+")");
		}*/

		if (!lastRound) {
			if (points.size()==2 && finalScore<BowlingGame.maxShotValue)
				roundType = RoundType.SIMPLE;
			if (points.size()==2 && finalScore==BowlingGame.maxShotValue) {
				roundType = RoundType.SPARE;
				score = BowlingGame.SPARE;
			}
			if (points.size()==1 && finalScore==BowlingGame.maxShotValue) {
				roundType = RoundType.STRIKE;
				score = BowlingGame.STRIKE;
			}
			
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

	
}
