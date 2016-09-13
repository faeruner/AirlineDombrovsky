package by.pvt.module3.command;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.resource.ConfigurationManager;

public class DispCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return ConfigurationManager.getProperty("path.page.user");

	}
}
