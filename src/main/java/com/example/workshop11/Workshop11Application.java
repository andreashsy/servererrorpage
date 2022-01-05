package com.example.workshop11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;

//import third party logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class Workshop11Application {

	// Instantiate logger
	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);
	// Default fallback port by spring application
	private static final String DEFAULT_PORT = "8082";
	public static void main(String[] args) {
		logger.info("workshop 11");
		// init spring app
		SpringApplication app = new SpringApplication(Workshop11Application.class);
		// decode the java app args using spring args helper
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		// return args from the javaargs as lits of string
		List optsVal = appArgs.getOptionValues("port");
		//var tohold up the port number to be passed on to the spring bootapp
		String portNumber = null;
		// check of opt args null or first element is null befire retrieving from the env var
		if (optsVal == null ||  optsVal.get(0) == null) {
			// retrueve from OS env var
			portNumber = System.getenv("PORT");

			// if os env var is null or empty, default to the default port
			if (portNumber = null) {
			portNumber =  DEFAULT_PORT;
			}
		} else {
			// if both not met, get from the args from the app
			portNumber = (String) optsVal.get(0);
		}

		if (portNumber != null) {
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}

		app.run(args);
	}

}
