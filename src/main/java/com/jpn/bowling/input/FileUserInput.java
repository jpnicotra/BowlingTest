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

import com.jpn.bowling.domain.PlayerChance;
import com.jpn.bowling.exceptions.IoGameException;
import com.jpn.bowling.exceptions.NoSuchFileGameException;
import com.jpn.games.exceptions.GameException;

@Component
public class FileUserInput implements UserInput{
	private List<PlayerChance> moves = null;
	private Map<String, List<String>> rolls;
	private int currentMove = 0;
	
	public FileUserInput () {
		
	}

	public void readInputs (String fileName) throws GameException {
        // read file into stream, try-with-resources
		// TODO CHECK ONLY VALID MOVEMENTS
		
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
        	rolls = stream.filter(line -> line.contains("\t"))
                	.map(line -> line.split("\t"))
                	 .collect(Collectors.groupingBy(i -> i[0], Collectors.mapping(val -> val[1], Collectors.toList())));
        }
        catch (NoSuchFileException notFound) {
        	throw new NoSuchFileGameException (notFound); 
        }
        catch (IOException ioException) {
        	throw new IoGameException (ioException); 
        }
	}
	

	public Map<String, List<String>> getRolls() {
		return rolls;
	}

}
