package com.sugon.test;

import java.io.InputStream;
import java.util.Properties;

public class Util {
	public static String ip;
	public static String username;
	public static String password;
	public static String install;
	public static String insertdataset;
	static {
		InputStream is = Util.class.getResourceAsStream("cofig.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			ip = properties.getProperty("ip");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			install = properties.getProperty("shell-install");
			insertdataset = properties.getProperty("shell-insertdataset");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
