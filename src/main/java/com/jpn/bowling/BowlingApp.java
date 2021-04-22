package com.jpn.bowling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jpn.bowling.components.impl.PlayerInfoClearFormatter;
import com.jpn.bowling.components.impl.PlayerInfoRequestedFormatter;
import com.jpn.games.components.PlayerInfoFormatter;

@SpringBootApplication
public class BowlingApp {
	private static final Logger LOGGER = LogManager.getLogger(BowlingApp.class);
	private static String formatter="";
	
	public static void main(String[] args) {
		if (args.length>=2)
			BowlingApp.formatter = args[1];
		SpringApplication app = new SpringApplication(BowlingApp.class);
		//app.setBannerMode(Banner.Mode.OFF);
		//app.setLogStartupInfo(false);
		app.run(args);
		
	}

    @Bean
    public PlayerInfoFormatter getPlayerInfoFormatter() {
    	switch (BowlingApp.formatter) {
	    	case "PlayerInfoClearFormatter": return new PlayerInfoClearFormatter();
	    	default: return new PlayerInfoRequestedFormatter();
    	}
    }
}
