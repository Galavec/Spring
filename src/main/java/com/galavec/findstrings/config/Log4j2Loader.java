package com.galavec.findstrings.config;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4j2Loader {
	static final Logger logger = LogManager.getLogger(Log4j2Loader.class.getName());

	private String log4j2DirectoryFile = "C:/wsFindStringsInFlatFiles/config/log4j2.xml";

	@Bean
	public String init() {
		String loggerConfig = log4j2DirectoryFile;
		var context = LoggerContext.getContext(false);
		var file = new File(loggerConfig);
		logger.info("Loading configuration log4j2..");
		context.setConfigLocation(file.toURI());
		logger.info("Loaded configuration log4j2 succesfully");

		logger.info("Se esta iniciando el servicio de:\n\n"
				+ "               ______ _           _  _____ _        _                 _____       ______ _       _   ______ _ _ \r\n"
				+ "              |  ____(_)         | |/ ____| |      (_)               |_   _|     |  ____| |     | | |  ____(_) | \r\n"
				+ " __      _____| |__   _ _ __   __| | (___ | |_ _ __ _ _ __   __ _ ___  | |  _ __ | |__  | | __ _| |_| |__   _| | ___  ___ \r\n"
				+ " \\ \\ /\\ / / __|  __| | | '_ \\ / _` |\\___ \\| __| '__| | '_ \\ / _` / __| | | | '_ \\|  __| | |/ _` | __|  __| | | |/ _ \\/ __| \r\n"
				+ "  \\ V  V /\\__ \\ |    | | | | | (_| |____) | |_| |  | | | | | (_| \\__ \\_| |_| | | | |    | | (_| | |_| |    | | |  __/\\__ \\ \r\n"
				+ "   \\_/\\_/ |___/_|    |_|_| |_|\\__,_|_____/ \\__|_|  |_|_| |_|\\__, |___/_____|_| |_|_|    |_|\\__,_|\\__|_|    |_|_|\\___||___/ \r\n"
				+ "                                                             __/ | \r\n"
				+ "                                                            |___/ \r\n" + "\nVersion: 1.3.0\n"
				+ "Developed by: Ing. Hector Galarza V.\n");

		return "OK";
	}
}
