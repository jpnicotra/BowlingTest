package com.jpn.bowling.domain;

import java.util.List;

import com.jpn.bowling.domain.Round.RoundType;

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
		if (errorMessage==null)
			this.calculateScore();
	}
	
	private void calculateScore() {
		for (int i=0;i<rounds.size();i++) {
			Round round = rounds.get(i);
			int score = (i == 0 ? 0 : rounds.get(i-1).getAccumulatedScore());
			if (round.getRoundType()==RoundType.SPARE || round.getRoundType()==RoundType.STRIKE) {
				if (round.getRoundType()==RoundType.SPARE) {
					Round addRound = rounds.get(i+1);
					score+=addRound.getPoints().get(0);
				}
				if (round.getRoundType()==RoundType.STRIKE) {
					// TODO CHECK SIZE
					// Get score of next round
					Round addRound = rounds.get(i+1);
					final List points = addRound.getPoints();
					java.util.List<Integer> addPoints=points.subList(0, (points.size()>=2 ? 2 : 1));
					
					// If this guy is a really PRO and did another Strike, I have to get value from next Round
					if (addPoints.size()<2 && (i+2)<rounds.size()) {
						addRound = rounds.get(i+2);
						addPoints.add(addRound.getPoints().get(0));
					}
					
					// Add all points from next rounds
					for (Integer addScore : addPoints) {
						score+=addScore;
					}
				}
			}
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
