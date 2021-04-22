package com.jpn.bowling.components.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpn.bowling.components.RollsTransformer;
import com.jpn.bowling.components.RoundsBuilder;
import com.jpn.bowling.exceptions.NoMovesException;
import com.jpn.bowling.input.UserInput;
import com.jpn.games.components.Game;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

@Component
public class BowlingGame implements Game {
	public static final int numberOfRounds=10;
	public static final String FAULT = "F";
	public static final String STRIKE = "X";
	public static final String SPARE = "/";
	public static final int maxShotValue = 10;
	public static final int maxShotsPerRound = 2;
	public static final int maxShotsLastRound = 3;
	
	@Autowired
	private RollsTransformer rollsToPlayerInfoTransformer;
	
	public BowlingGame() {
	}

	public List<PlayerInfo> newGame(UserInput userInput) throws GameException {
		if (userInput == null)
			throw new NoMovesException();
		// Get all rolls send by the user
		final Map<String, List<String>> rolls = userInput.getRolls();
		final List<PlayerInfo> players = rollsToPlayerInfoTransformer.transformRollsToPlayerInfo(rolls);
		
		String strRounds = "";
		for (int i=1;i<=numberOfRounds;i++)
			strRounds+="\t"+i;
		final String stringRounds = strRounds;
		for (int i=0;i<players.size();i++) {
			PlayerInfo player = players.get(i);
			System.out.println("Round\t"+stringRounds);
			System.out.println(player.getPlayerName());
			if (player.getErrorMessage()!=null) {
				System.out.println("Exception found! "+player.getErrorMessage());
			}
			else {
				final String stringRound = player.getRounds().stream().map(round -> round.toString()).reduce((str, round) -> str+="\t"+round).get();
				System.out.println("Pinfalls\t"+stringRound);
				System.out.print("Score\t");
				player.getRounds().forEach(round -> System.out.print ("\t"+round.getAccumulatedScore()));
				System.out.println("");
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		}
		return players;
	}
}
