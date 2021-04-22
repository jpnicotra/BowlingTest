package com.jpn.bowling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.jpn.bowling.exceptions.NoSuchFileGameException;
import com.jpn.bowling.input.UserInput;
import com.jpn.games.components.Game;
import com.jpn.games.exceptions.GameException;

@Profile("!test")
@Component
public class BowlingCommandLineRunnerApp implements CommandLineRunner {
	private static final Logger LOGGER = LogManager.getLogger(BowlingCommandLineRunnerApp.class);
	
	@Autowired
	private UserInput fileUserInput;
	
	@Autowired
	private Game bowlingGame;
	
	@Override
    public void run(String... args) {
		// Check if user sent at least one file
	    if (args!=null && args.length <= 0) {
	    	LOGGER.info("Please provide at least one path to a valid file. Example: data/sample-moves.txt");
	        System.exit(-1);
	      }

	    for (String dataFile : args) {
			try {
				fileUserInput.readInputs(dataFile);
				bowlingGame.newGame(fileUserInput);
			}
			catch (NoSuchFileGameException nsfe) {
				LOGGER.error("File '{}' not found. Verify file location and try again", dataFile);
			}
			catch (GameException e) {
				LOGGER.error(e);
				e.printStackTrace();
			}
	    }
	}
}
