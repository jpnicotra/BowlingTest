package com.jpn.bowling.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.jpn.bowling.exceptions.IoGameException;
import com.jpn.bowling.exceptions.NoSuchFileGameException;
import com.jpn.games.domain.UserInput;
import com.jpn.games.exceptions.GameException;

/**
 * Specific implementation of UserInput used to read moves from a file. Each
 * line represents a player and a chance with the subsequent number of pins
 * knocked down. An 'F' indicates a foul on that chance and no pins knocked down
 * (identical for scoring to a roll of 0). The rows are tab-separated.
 * 
 * @author jnicotra
 *
 */
@Component
public class FileUserInput implements UserInput {
	private Map<String, List<String>> rolls;

	public FileUserInput() {

	}

	/**
	 * Specific implementation of UserInput used to read moves from a file. Each
	 * line represents a player and a chance with the subsequent number of pins
	 * knocked down. An 'F' indicates a foul on that chance and no pins knocked down
	 * (identical for scoring to a roll of 0). The rows are tab-separated.
	 * 
	 * @param fileName Full path to file
	 * @throws GameException In case file doesn't exists or some other IO exceptions
	 *                       is generated
	 */
	public void readInputs(String fileName) throws GameException {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			rolls = stream.filter(line -> line.contains("\t")).map(line -> line.split("\t"))
					.collect(Collectors.groupingBy(i -> i[0], Collectors.mapping(val -> val[1], Collectors.toList())));
		} catch (NoSuchFileException notFound) {
			throw new NoSuchFileGameException(notFound);
		} catch (IOException ioException) {
			throw new IoGameException(ioException);
		}
	}

	/**
	 * Get rolls generated after reading file return Map of users with a list of
	 * string scores
	 */
	public Map<String, List<String>> getRolls() {
		return rolls;
	}

}
