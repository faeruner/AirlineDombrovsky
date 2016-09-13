package by.pvt.module3.resource;

import java.util.ResourceBundle;

public class MessageManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

	// messages. properties
	private MessageManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}