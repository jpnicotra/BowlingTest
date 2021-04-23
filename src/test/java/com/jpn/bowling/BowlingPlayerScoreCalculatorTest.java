package com.jpn.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jpn.bowling.components.RollsTransformer;
import com.jpn.bowling.domain.BowlingPlayerInfo;
import com.jpn.bowling.domain.Round;
import com.jpn.games.components.PlayerScoreCalculator;
import com.jpn.games.domain.PlayerInfo;

@ActiveProfiles("test")
@SpringBootTest()
public class BowlingPlayerScoreCalculatorTest {
	private static final Logger LOGGER = LogManager.getLogger(BowlingPlayerScoreCalculatorTest.class);

	@Autowired
	private RollsTransformer rollsTransformer;

	@Autowired
	private PlayerScoreCalculator bowlingPlayerScoreCalculator;

	@Test
	public void testAllTypesOfRound() {
		HashMap<String, List<String>> rolls = new HashMap<String, List<String>>();
		rolls.put("Carl 1", Arrays.asList("F", "10", "1", "0", "5", "5", "10", "5", "4", "5", "4", "5", "4", "5", "4", "5", "4", "5", "4"));
		List<PlayerInfo> players = rollsTransformer.transformRollsToPlayerInfo(rolls);

		BowlingPlayerInfo player1 = (BowlingPlayerInfo)players.get(0);
		List<Round> rounds = player1.getRounds();
		int score;
		
		// Checking a SPARE in first ROUND!
		score = bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(0));
		assertEquals(11, score);
		rounds.get(0).setAccumulatedScore(score);
		
		// Checking a SIMPLE second ROUND!
		score = bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(1));
		assertEquals(12, score);
		rounds.get(1).setAccumulatedScore(score);
		
		// Checking another SPARE in third ROUND!
		score = bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(2));
		assertEquals(32, score);
		rounds.get(2).setAccumulatedScore(score);
		
		// Checking a STRIKE in fourth ROUND!
		score = bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(3));
		assertEquals(51, score);
		rounds.get(3).setAccumulatedScore(score);
	}


	@Test
	public void testEndingStrikesRound() {
		HashMap<String, List<String>> rolls = new HashMap<String, List<String>>();
		rolls.put("Carl 1", Arrays.asList("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","10","10","10","10"));
		List<PlayerInfo> players = rollsTransformer.transformRollsToPlayerInfo(rolls);

		BowlingPlayerInfo player1 = (BowlingPlayerInfo)players.get(0);
		List<Round> rounds = player1.getRounds();
		int score;

		for (int i=0;i<9;i++) {
			rounds.get(i).setAccumulatedScore(bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(i)));
		}
		
		// Checking all STRIKES last round!
		score = bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(9));
		assertEquals(50, score);
		rounds.get(9).setAccumulatedScore(score);
	}

	@Test
	public void testEndingStrikesWithSimpleRound() {
		HashMap<String, List<String>> rolls = new HashMap<String, List<String>>();
		rolls.put("Carl 1", Arrays.asList("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","10","10","10","5"));
		List<PlayerInfo> players = rollsTransformer.transformRollsToPlayerInfo(rolls);

		BowlingPlayerInfo player1 = (BowlingPlayerInfo)players.get(0);
		List<Round> rounds = player1.getRounds();
		int score;

		for (int i=0;i<9;i++) {
			rounds.get(i).setAccumulatedScore(bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(i)));
		}
		
		// Checking all STRIKES last round!
		score = bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(9));
		assertEquals(45, score);
		rounds.get(9).setAccumulatedScore(score);
	}

	@Test
	public void testEndingStrikesWithSimpleInMiddleRound() {
		HashMap<String, List<String>> rolls = new HashMap<String, List<String>>();
		rolls.put("Carl 1", Arrays.asList("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","10","10","5","5"));
		List<PlayerInfo> players = rollsTransformer.transformRollsToPlayerInfo(rolls);

		BowlingPlayerInfo player1 = (BowlingPlayerInfo)players.get(0);
		List<Round> rounds = player1.getRounds();
		int score;

		for (int i=0;i<9;i++) {
			rounds.get(i).setAccumulatedScore(bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(i)));
		}
		
		// Checking all STRIKES last round!
		score = bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(9));
		assertEquals(45, score);
		rounds.get(9).setAccumulatedScore(score);
	}

	@Test
	public void testEndingSpareWithStrikeRound() {
		HashMap<String, List<String>> rolls = new HashMap<String, List<String>>();
		rolls.put("Carl 1", Arrays.asList("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","10","3","7","10"));
		List<PlayerInfo> players = rollsTransformer.transformRollsToPlayerInfo(rolls);

		BowlingPlayerInfo player1 = (BowlingPlayerInfo)players.get(0);
		List<Round> rounds = player1.getRounds();
		int score;

		for (int i=0;i<9;i++) {
			rounds.get(i).setAccumulatedScore(bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(i)));
		}
		
		// Checking all STRIKES last round!
		score = bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(9));
		assertEquals(33, score);
		rounds.get(9).setAccumulatedScore(score);
	}

	@Test
	public void testEndingSpareWithSimpleRound() {
		HashMap<String, List<String>> rolls = new HashMap<String, List<String>>();
		rolls.put("Carl 1", Arrays.asList("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","10","3","7","6"));
		List<PlayerInfo> players = rollsTransformer.transformRollsToPlayerInfo(rolls);

		BowlingPlayerInfo player1 = (BowlingPlayerInfo)players.get(0);
		List<Round> rounds = player1.getRounds();
		int score;

		for (int i=0;i<9;i++) {
			rounds.get(i).setAccumulatedScore(bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(i)));
		}
		
		// Checking all STRIKES last round!
		score = bowlingPlayerScoreCalculator.calculateScore(player1, rounds.get(9));
		assertEquals(29, score);
		rounds.get(9).setAccumulatedScore(score);
	}

}
