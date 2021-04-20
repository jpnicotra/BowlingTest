package com.jpn.bowling.ui;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpn.bowling.game.BowlingGame;
import com.jpn.bowling.input.FileUserInput;


public class UserInputTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInputTest.class);

	public static void main(String[] args) {
		FileUserInput input = new FileUserInput();
		input.readFile("data"+File.separator+"sample-moves.txt");
		BowlingGame game = new BowlingGame();
		try {
			game.newGame(input);

			FileUserInput inputCrash = new FileUserInput();
			inputCrash.readFile("data"+File.separator+"crash-rounds.txt");
			game.newGame(inputCrash);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
