package com.jpn.bowling.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpn.bowling.game.BowlingGame;
import com.jpn.bowling.input.FileUserInput;


public class BowlingApp {
	private static final Logger LOGGER = LoggerFactory.getLogger(BowlingApp.class);
	
	public static void main(String[] args) {
		// Check if user sent at least one file
	    if (args!=null && args.length <= 0) {
	    	LOGGER.info("Please provide at least one path to a valid file. Example: data/sample-moves.txt");
	        System.exit(-1);
	      }

	    // TODO Handle Exceptions!
	    for (String dataFile : args) {
			try {
				FileUserInput input = new FileUserInput();
				input.readFile(dataFile);
				BowlingGame game = new BowlingGame();
				game.newGame(input);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
}
