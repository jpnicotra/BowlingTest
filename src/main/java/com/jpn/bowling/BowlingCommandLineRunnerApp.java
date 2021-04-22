package com.jpn.bowling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.jpn.bowling.exceptions.NoSuchFileGameException;
import com.jpn.games.components.Game;
import com.jpn.games.components.PlayerInfoFormatter;
import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.domain.UserInput;
import com.jpn.games.exceptions.GameException;

/**
 * This class execute the application through command line using at least one
 * argument (input text file)
 * 
 * @author jnicotra
 * @see CommandLineRunner
 */
@Profile("!test")
@Component
public class BowlingCommandLineRunnerApp implements CommandLineRunner {
	private static final Logger LOGGER = LogManager.getLogger(BowlingCommandLineRunnerApp.class);

	@Autowired
	private UserInput fileUserInput;

	@Autowired
	private Game bowlingGame;

	@Autowired
	private PlayerInfoFormatter playerInfoFormatter;

	/**
	 * This methods execute the application through command line using at least one
	 * argument (input text file)
	 */
	@Override
	public void run(String... args) {
		// Check if user sent at least one file
		if (args != null && args.length <= 0) {
			LOGGER.info("Please provide at least one path to a valid file. Example: data/sample-moves.txt");
			System.exit(-1);
		}

		if (args.length >= 1) {
			String dataFile = args[0];
			try {
				fileUserInput.readInputs(dataFile);
				java.util.List<PlayerInfo> players = bowlingGame.newGame(fileUserInput);
				LOGGER.info(playerInfoFormatter.formatPlayersInfo(players));
			} catch (NoSuchFileGameException nsfe) {
				LOGGER.error("File '{}' not found. Verify file location and try again", dataFile);
			} catch (GameException e) {
				LOGGER.error(e);
				e.printStackTrace();
			}
		}

		System.exit(0);
	}
}
