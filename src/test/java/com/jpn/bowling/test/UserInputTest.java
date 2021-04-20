package com.jpn.bowling.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jpn.bowling.domain.PlayerChance;
import com.jpn.bowling.input.FileUserInput;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserInputTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInputTest.class);

	@Autowired
	private FileUserInput input;

	@Before
	public void setUp() {
		Assert.assertNotNull(input);
	}

	@Test
	public void testFileInputValid() {
		try {
			input.readFile("data"+File.separator+"sample-moves.txt");
			
			PlayerChance chance=input.nextMove();
			while (chance!=null) {
				System.out.println (chance.getPlayer()+": "+chance.getKnockedDownCount());
				
				Assert.assertNotNull(chance);
				chance=input.nextMove();
			}
			
		}
		catch (Exception e) {
			Assert.fail(e.toString());
		}
		
	}

	@Configuration
	static class Config {
		@Bean
		public FileUserInput input() {
			return new FileUserInput();
		}
	}
}
