package by.pvt.module3.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

	// config. properties
	private ConfigurationManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}