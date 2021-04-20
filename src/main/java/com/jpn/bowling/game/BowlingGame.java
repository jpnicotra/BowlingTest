package com.jpn.bowling.game;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpn.bowling.domain.PlayerInfo;
import com.jpn.bowling.input.UserInput;

public class BowlingGame {
	public static final int numberOfRounds=10;
	public static final String FAULT = "F";
	public static final String STRIKE = "X";
	public static final String SPARE = "/";
	public static final int maxShotValue = 10;
	
	public BowlingGame() {

	}

	public void newGame(UserInput userInput) throws Exception {
		// TODO CHANGE EXCEPTION
		if (userInput == null)
			throw new Exception("NO MOVES");
		final Map<String, List<String>> rolls = userInput.getRolls();
		final List<PlayerInfo> players = rolls.entrySet().stream().map(e -> new PlayerInfo(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		players.stream().forEach(player -> player.buildRounds());
		
		String strRounds = "";
		for (int i=1;i<=numberOfRounds;i++)
			strRounds+="\t"+i;
		final String stringRounds = strRounds;
		for (int i=0;i<players.size();i++) {
			PlayerInfo player = players.get(i);
			System.out.println("Round\t"+stringRounds);
			System.out.println(player.getName());
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
	}
}
