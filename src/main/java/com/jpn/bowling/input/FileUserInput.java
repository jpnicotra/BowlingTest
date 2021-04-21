package com.jpn.bowling.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jpn.bowling.domain.PlayerChance;

public class FileUserInput implements UserInput{
	private List<PlayerChance> moves = null;
	private Map<String, List<String>> rolls;
	private int currentMove = 0;
	
	public FileUserInput () {
		
	}
	
	public void readFile (String fileName) throws NoSuchFileException, IOException {
        // read file into stream, try-with-resources
		// TODO CHECK ONLY VALID MOVEMENTS
		
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
        	rolls = stream.filter(line -> line.contains("\t"))
                	.map(line -> line.split("\t"))
                	 .collect(Collectors.groupingBy(i -> i[0], Collectors.mapping(val -> val[1], Collectors.toList())));
        }
        catch (NoSuchFileException notFound) {
        	throw notFound; 
        }
	}
	

	public Map<String, List<String>> getRolls() {
		return rolls;
	}

	public PlayerChance nextMove() {
		if (moves==null || currentMove>=moves.size())
			return null;
		
		PlayerChance nextMove = moves.get(currentMove);
		currentMove+=1;
		return nextMove;
	}

}
