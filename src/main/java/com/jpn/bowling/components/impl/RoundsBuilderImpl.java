package com.jpn.bowling.components.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RoundsBuilder;
import com.jpn.bowling.domain.Round;
import com.jpn.bowling.domain.Round.RoundType;
import com.jpn.bowling.exceptions.NotEnoughRollsException;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

@Component
public class RoundsBuilderImpl implements RoundsBuilder {

	public PlayerInfo buildRounds(PlayerInfo player) throws GameException {
		Round currentRound = null;
		java.util.List<Round> rounds = new java.util.ArrayList<Round>();
		final java.util.List<String> scores = player.getScores();
		for (String score : scores) {
			if (currentRound==null) {
				currentRound = new Round(rounds.size()+1);
				rounds.add(currentRound);
			}
			currentRound.addScore(score);
			
			if (currentRound.isFull()) {
				currentRound = null;
			}
		}
		
		player.setRounds(rounds);
		// TODO Solve error message
		if (rounds.size()!=BowlingGame.numberOfRounds)
			throw new NotEnoughRollsException();

		if (player.getErrorMessage()==null)
			calculateScore(player);
		
		return player;
	}

	private void calculateScore(PlayerInfo player) {
		final java.util.List<Round> rounds = player.getRounds();
		for (int i=0;i<rounds.size();i++) {
			Round round = rounds.get(i);
			// Start with score 0 if this is first round
			int score = 0;
			if (i>0) {
				score += rounds.get(i-1).getAccumulatedScore();
			}
			
			if (round.getRoundType()==RoundType.STRIKE) {
				score += calculateStrike(rounds, i, round);
			}
			if (round.getRoundType()==RoundType.SPARE) {
				score += calculateSpare(rounds, i, round);
			}
			/*
			 */
			score+=round.getFinalScore();
		
			round.setAccumulatedScore(score);
		}
	}

	
	private int calculateSpare(java.util.List<Round> rounds, int index, Round round) {
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
	
	private int calculateStrike(java.util.List<Round> rounds, int index, Round round) {
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

}
