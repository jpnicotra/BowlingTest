package com.jpn.bowling.domain;

import java.util.List;

import com.jpn.bowling.domain.Round.RoundType;
import com.jpn.bowling.game.BowlingGame;

public class PlayerInfo {
	private final String name;
	private final List<String> scores;
	private java.util.List<Round> rounds;
	private String errorMessage;
	
	public PlayerInfo (String name, List<String> scores) {
		this.name = name;
		this.scores = scores;
	}

	public String getName() {
		return name;
	}

	public List<String> getScores() {
		return scores;
	}
	
	public void buildRounds() {
		errorMessage = null;
		Round currentRound = null;
		rounds = new java.util.ArrayList<Round>();
		
		for (String score : scores) {
			if (currentRound==null) {
				currentRound = new Round(rounds.size()+1);
				rounds.add(currentRound);
			}
			try {
				currentRound.addScore(score);
			}
			catch (Exception e) {
				errorMessage = e.getMessage();
				return ;
			}
			
			if (currentRound.isFull()) {
				currentRound = null;
			}
		}
		
		// TODO Solve error message
		if (rounds.size()!=BowlingGame.numberOfRounds)
			errorMessage = "This game doesn't have enough shoots.";
		if (errorMessage==null)
			this.calculateScore();
	}
	
	private int calculateSpare(int index, Round round) {
		int score = 0;
		
		if (round.isLastRound()) {
			score+=round.getPoints().get(round.getPoints().size()-1);
		}
		else {
			Round addRound = rounds.get(index+1);
			score+=addRound.getPoints().get(0);
		}
		
		return score;
	}
	
	private int calculateStrike(int index, Round round) {
		int score = 0;
		
		if (round.getRoundType()==RoundType.STRIKE) {
			if (round.isLastRound()) {
				score+=round.getPoints().get(round.getPoints().size()-1);
			}
			else {
				// TODO CHECK SIZE
				// Get score of next round
				Round addRound = rounds.get(index+1);
				final List points = addRound.getPoints();
				int maxPoints = (addRound.isLastRound() ? 2 : points.size() );
				java.util.List<Integer> addPoints=points.subList(0, (points.size()>=maxPoints ? maxPoints : 1));
				
				// If this guy is a really PRO and did another Strike, I have to get value from next Round
				if (addPoints.size()<2 && (index+2)<rounds.size()) {
					addRound = rounds.get(index+2);
					addPoints.add(addRound.getPoints().get(0));
				}
				// Add all points from next rounds
				for (Integer addScore : addPoints) {
					score+=addScore;
				}
			}
		}
		
		return score;
	}
	private void calculateScore() {
		for (int i=0;i<rounds.size();i++) {
			Round round = rounds.get(i);
			// Start with score 0 if this is first round
			int score = 0;
			if (i>0) {
				score += rounds.get(i-1).getAccumulatedScore();
			}
			
			if (round.getRoundType()==RoundType.STRIKE) {
				score += calculateStrike(i, round);
			}
			if (round.getRoundType()==RoundType.SPARE) {
				score += calculateSpare(i, round);
			}
			/*
			 */
			score+=round.getFinalScore();
		
			round.setAccumulatedScore(score);
		}
	}

	public java.util.List<Round> getRounds() {
		return rounds;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
