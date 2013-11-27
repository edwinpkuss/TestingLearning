package com.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyApp {
	static Logger logger = LogManager.getLogger(MyApp.class.getName());
	
	public static void main(String[] args){
		logger.trace("Entering application");
		
	}
}
