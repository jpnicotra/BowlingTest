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
import com.jpn.games.domain.PlayerInfo;

@ActiveProfiles("test")
@SpringBootTest()
public class RollsTransformerTest {
	private static final Logger LOGGER = LogManager.getLogger(RollsTransformerTest.class);

	@Autowired
	private RollsTransformer rollsTransformer;
	private List<PlayerInfo> players;
	private List<PlayerInfo> playersScore;

	@BeforeEach
	public void setUp() {
		HashMap<String, List<String>> rolls = new HashMap<String, List<String>>();
		rolls.put("Carl 1", Arrays.asList("F", "10", "1", "0", "5", "5", "5", "5", "5", "4", "5", "4", "5", "4", "5",
				"4", "5", "4", "5", "4"));
		rolls.put("Carl 2", Arrays.asList("10", "3", "7", "0", "F", "5", "5", "5", "5", "4", "5", "4", "5", "4", "5",
				"4", "5", "4", "5", "4"));
		players = rollsTransformer.transformRollsToPlayerInfo(rolls);

		HashMap<String, List<String>> rollsScore = new HashMap<String, List<String>>();
		rollsScore.put("Carl 1", Arrays.asList("F", "10", "1", "0", "5", "5", "5", "5", "5", "4", "5", "4", "5", "4",
				"5", "4", "5", "4", "5", "4"));
		rollsScore.put("Carl 2", Arrays.asList("10", "3", "7", "0", "F", "5", "5", "5", "5", "4", "5", "4", "5", "4",
				"5", "4", "5", "4", "5", "4"));
		rollsScore.put("Carl 3", Arrays.asList("10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"));
		playersScore = rollsTransformer.transformRollsToPlayerInfo(rollsScore);
	}

	@Test
	public void testValidPlayers() {
		assertEquals(2, players.size());
		PlayerInfo player1 = players.get(0);
		PlayerInfo player2 = players.get(1);
		assertEquals("Carl 1", player1.getPlayerName());
		assertEquals("Carl 2", player2.getPlayerName());

	}

	@Test
	public void testValidRounds() {
		BowlingPlayerInfo player1 = (BowlingPlayerInfo) players.get(0);
		BowlingPlayerInfo player2 = (BowlingPlayerInfo) players.get(1);
		final List<Round> rounds1 = player1.getRounds();
		final List<Round> rounds2 = player2.getRounds();
		assertEquals(rounds2.size(), rounds1.size());

	}

	@Test
	public void testValidRoundsScores() {
		BowlingPlayerInfo player1 = (BowlingPlayerInfo) playersScore.get(0);
		BowlingPlayerInfo player2 = (BowlingPlayerInfo) playersScore.get(1);
		BowlingPlayerInfo player3 = (BowlingPlayerInfo) playersScore.get(2);
		final List<Round> rounds1 = player1.getRounds();
		final List<Round> rounds2 = player2.getRounds();
		final List<Round> rounds3 = player3.getRounds();
		assertEquals("[F /, 1 0, 5 /, 5 /, 5 4, 5 4, 5 4, 5 4, 5 4, 5 4]", rounds1.toString());
		assertEquals("[X, X, X, X, X, X, X, X, X, X X X]", rounds2.toString());
		assertEquals("[X, 3 /, 0 F, 5 /, 5 /, 4 5, 4 5, 4 5, 4 5, 4 5 4]", rounds3.toString());

	}
}
