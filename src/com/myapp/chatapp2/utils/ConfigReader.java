package com.myapp.chatapp2.utils;

import java.util.ResourceBundle;

public class ConfigReader {

	private static ResourceBundle rb  = ResourceBundle.getBundle("config");
	
	public static String getValue(String key) {
		return rb.getString(key);
	}
}
