package com.jpn.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jpn.bowling.components.score.ScoreController;
import com.jpn.bowling.domain.Round;
import com.jpn.bowling.exceptions.ExceedMaximumNumberOfRollsException;
import com.jpn.bowling.exceptions.InvalidScoreException;
import com.jpn.bowling.exceptions.ScoreOutOfRangeException;

@ActiveProfiles("test")
@SpringBootTest()
public class BowlingScoreControllerTest {
	private static final Logger LOGGER = LogManager.getLogger(BowlingScoreControllerTest.class);


	@Autowired
	private ScoreController bowlingScoreController;

	@Test
	public void testAddStrikeScore() {
		Round round = new Round (1);
		bowlingScoreController.addScore(round, "10");
		assertEquals(10, round.getFinalScore());
	}

	@Test
	public void testAddFaultScore() {
		Round round = new Round (1);
		bowlingScoreController.addScore(round, "F");
		assertEquals(0, round.getFinalScore());
	}

	@Test
	public void testInvalidScoreCharacter() {
		Round round = new Round (1);
		assertThrows(InvalidScoreException.class, () -> bowlingScoreController.addScore(round, "X"));
	}

	@Test
	public void testInvalidScoreNegative() {
		Round round = new Round (1);
		assertThrows(ScoreOutOfRangeException.class, () -> bowlingScoreController.addScore(round, "-10"));
	}

	@Test
	public void testExceedMaximumNumberOfRollsException() {
		Round round = new Round (5);
		bowlingScoreController.addScore(round, "2");
		bowlingScoreController.addScore(round, "2");
		assertThrows(ExceedMaximumNumberOfRollsException.class, () -> bowlingScoreController.addScore(round, "10"));
	}
	
	@Test
	public void testExceedMaximumNumberOfRollsExceptionLastRound() {
		Round round = new Round (10);
		bowlingScoreController.addScore(round, "2");
		bowlingScoreController.addScore(round, "2");
		bowlingScoreController.addScore(round, "2");
		assertThrows(ExceedMaximumNumberOfRollsException.class, () -> bowlingScoreController.addScore(round, "10"));
	}
	

}
