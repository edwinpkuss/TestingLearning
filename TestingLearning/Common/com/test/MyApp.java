package com.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.Configuration;
import util.Constants;

public class MyApp {
	static Logger logger = LogManager.getLogger(MyApp.class.getName());
	
	private String base_url = Configuration.getInstance().getProperty(Constants.BASE_URL);
	private String user_name = Configuration.getInstance().getProperty(Constants.USER_NAME);
	private String password = Configuration.getInstance().getProperty(Constants.PASSWORD);
	
	public void getBaseURL(){
		System.out.println(base_url);
		System.out.println(user_name);
		System.out.println(password);
	}
	
	public static void main(String[] args){
		logger.trace("Entering application");
		
		new MyApp().getBaseURL();
		
	}
}
