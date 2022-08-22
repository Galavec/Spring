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

	private String log4j2DirectoryFile = "D:/Proyectos/Spring/wsFindStringsInFlatFiles/config/log4j2.xml";

	@Bean
	public String init() {
		String loggerConfig = log4j2DirectoryFile;
		LoggerContext context = LoggerContext.getContext(false);
		File file = new File(loggerConfig);
		logger.info("Loading configuration log4j2..");
		context.setConfigLocation(file.toURI());
		logger.info("Loaded configuration log4j2 succesfully");

		logger.info("Se esta iniciando el servicio de:\n\n"
				+ " ___                       ___         _               ___      _ \r\n"
				+ "| _ )_  _ ___ __ __ _ _ _ / __|__ _ __| |___ _ _  __ _| __|_ _ | |   ___  __ _ \r\n"
				+ "| _ \\ || (_-</ _/ _` | '_| (__/ _` / _` / -_) ' \\/ _` | _|| ' \\| |__/ _ \\/ _` | \r\n"
				+ "|___/\\_,_/__/\\__\\__,_|_|  \\___\\__,_\\__,_\\___|_||_\\__,_|___|_||_|____\\___/\\__, | \r\n"
				+ "                                                                          |___/ \r\n"
				+ "\nVersion: 1.21\n" + "Desarrollado por: HTS Hector Galarza V.\n");

		return "OK";
	}
}
