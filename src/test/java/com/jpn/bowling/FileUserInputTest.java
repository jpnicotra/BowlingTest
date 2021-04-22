package com.jpn.bowling;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpn.bowling.domain.PlayerChance;
import com.jpn.bowling.exceptions.NotEnoughRollsException;
import com.jpn.bowling.input.UserInput;
import com.jpn.games.components.Game;
import com.jpn.games.exceptions.GameException;


@ActiveProfiles("test")
@SpringBootTest()
public class FileUserInputTest {
	private static final Logger LOGGER = LogManager.getLogger(FileUserInputTest.class);

	@Autowired
	private UserInput fileUserInput;
	
	@Test
	void testCleanFile() {
		fileUserInput.readInputs("data"+File.separator+"test"+File.separator+"clean-file.txt");
		final Map<String, List<String>> rolls = fileUserInput.getRolls();
		
		assertEquals(0, rolls.size());
	}


	@Test
	public void testFileInputValid() {
		fileUserInput.readInputs("data"+File.separator+"test"+File.separator+"one-chance.txt");

		final Map<String, List<String>> rolls = fileUserInput.getRolls();
		
		assertEquals(1, rolls.size());
	}

	@Test()
	public void testFileInputInvalid() {
		assertThrows(GameException.class, () -> {
			fileUserInput.readInputs("data"+File.separator+"doesnt_exist.txt");
		});
	}
}
