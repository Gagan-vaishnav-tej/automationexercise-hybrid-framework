package com.automationexercise.ui.utils;

import config.ConfigReader;

public class RandomUtil {
	
	public static String generateEmail() {
		
		return ConfigReader.getProperty("register.email") + System.currentTimeMillis() + "@test.com";
	}
	
	public static String generatePassword() {
		return ConfigReader.getProperty("register.password") + System.currentTimeMillis();
	}
}
