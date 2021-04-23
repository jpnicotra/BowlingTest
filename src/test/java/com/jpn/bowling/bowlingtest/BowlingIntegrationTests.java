package com.jpn.bowling.bowlingtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jpn.bowling.exceptions.ExceedMaximumNumberOfRollsException;
import com.jpn.bowling.exceptions.NotEnoughRollsException;
import com.jpn.bowling.exceptions.ScoreExceedsAllowedValueException;
import com.jpn.bowling.exceptions.ScoreOutOfRangeException;
import com.jpn.games.components.Game;
import com.jpn.games.components.PlayerInfoFormatter;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.domain.UserInput;

@ActiveProfiles("test")
@SpringBootTest()
class BowlingIntegrationTests {

	@Autowired
	private UserInput fileUserInput;

	@Autowired
	private Game bowlingGame;
	@Autowired
	private PlayerInfoFormatter playerInfoRequestedFormatter;

	@Test
	void testAllStrikes() {
		fileUserInput.readInputs("data/test/all-strikes.txt");
		List<PlayerInfo> players = bowlingGame.newGame(fileUserInput);

		assertEquals(300, players.get(0).getFinalScore());

		final String expectedResult = "Frame		1		2		3		4		5		6		7		8		9		10\r\n"
				+ "Carl\r\n"
				+ "Pinfalls		X		X		X		X		X		X		X		X		X	X	X	X\r\n"
				+ "Score		30		60		90		120		150		180		210		240		270		300\r\n";

		assertEquals(expectedResult, bowlingGame.formatGame(players, playerInfoRequestedFormatter));
	}

	@Test
	void testAllFaults() {
		fileUserInput.readInputs("data/test/all-faults.txt");
		List<PlayerInfo> players = bowlingGame.newGame(fileUserInput);

		assertEquals(0, players.get(0).getFinalScore());

		final String expectedResult = "Frame		1		2		3		4		5		6		7		8		9		10\r\n"
				+ "Carl\r\n"
				+ "Pinfalls	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F	F\r\n"
				+ "Score		0		0		0		0		0		0		0		0		0		0\r\n";

		assertEquals(expectedResult, bowlingGame.formatGame(players, playerInfoRequestedFormatter));
	}

	@Test
	void testAllZeros() {
		fileUserInput.readInputs("data/test/all-zeros.txt");
		List<PlayerInfo> players = bowlingGame.newGame(fileUserInput);

		assertEquals(0, players.get(0).getFinalScore());
		final String expectedResult = "Frame		1		2		3		4		5		6		7		8		9		10\r\n"
				+ "Carl\r\n"
				+ "Pinfalls	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0\r\n"
				+ "Score		0		0		0		0		0		0		0		0		0		0\r\n";

		assertEquals(expectedResult, bowlingGame.formatGame(players, playerInfoRequestedFormatter));
	}

	@Test
	void testAllSpares() {
		fileUserInput.readInputs("data/test/all-spares.txt");
		List<PlayerInfo> players = bowlingGame.newGame(fileUserInput);

		assertEquals(150, players.get(0).getFinalScore());

		final String expectedResult = "Frame		1		2		3		4		5		6		7		8		9		10\r\n"
				+ "Carl\r\n"
				+ "Pinfalls	5	/	5	/	5	/	5	/	5	/	5	/	5	/	5	/	5	/	5	/	5\r\n"
				+ "Score		15		30		45		60		75		90		105		120		135		150\r\n";

		assertEquals(expectedResult, bowlingGame.formatGame(players, playerInfoRequestedFormatter));
	}

	@Test
	void testAllSparesEndWithStrike() {
		fileUserInput.readInputs("data/test/all-spares2.txt");
		List<PlayerInfo> players = bowlingGame.newGame(fileUserInput);

		assertEquals(155, players.get(0).getFinalScore());

		final String expectedResult = "Frame		1		2		3		4		5		6		7		8		9		10\r\n"
				+ "Carl\r\n"
				+ "Pinfalls	5	/	5	/	5	/	5	/	5	/	5	/	5	/	5	/	5	/	5	/	X\r\n"
				+ "Score		15		30		45		60		75		90		105		120		135		155\r\n";

		assertEquals(expectedResult, bowlingGame.formatGame(players, playerInfoRequestedFormatter));
	}

	@Test
	void testFileExample() {
		fileUserInput.readInputs("data/test/test-example.txt");
		List<PlayerInfo> players = bowlingGame.newGame(fileUserInput);

		assertEquals(151, players.get(0).getFinalScore());
		assertEquals(167, players.get(1).getFinalScore());
	}

	@Test
	void testScoreOutOfRangeException() {
		fileUserInput.readInputs("data/test/ScoreOutOfRangeException.txt");
		assertThrows(ScoreOutOfRangeException.class, () -> bowlingGame.newGame(fileUserInput));
	}
	@Test
	void testScoreUnderZeroException() {
		fileUserInput.readInputs("data/test/values_under_zero.txt");
		assertThrows(ScoreOutOfRangeException.class, () -> bowlingGame.newGame(fileUserInput));
	}

	@Test
	void testScoreExceedsAllowedValueException() {
		fileUserInput.readInputs("data/test/ScoreExceedsAllowdValueException.txt");
		assertThrows(ScoreExceedsAllowedValueException.class, () -> bowlingGame.newGame(fileUserInput));
	}

	@Test
	void testScoreExceedsAllowedValueExceptionLastRoll() {
		fileUserInput.readInputs("data/test/ScoreExceedsAllowdValueExceptionLastRoll.txt");
		assertThrows(ScoreExceedsAllowedValueException.class, () -> bowlingGame.newGame(fileUserInput));
	}

	@Test
	void testExceedMaximumNumberOfRollsException() {
		fileUserInput.readInputs("data/test/ExceedMaximumNumberOfRollsException.txt");
		assertThrows(ExceedMaximumNumberOfRollsException.class, () -> bowlingGame.newGame(fileUserInput));
	}

	@Test
	void testNotEnoughRollsException() {
		fileUserInput.readInputs("data/test/NotEnoughRollsException.txt");
		assertThrows(NotEnoughRollsException.class, () -> bowlingGame.newGame(fileUserInput));
	}
}
