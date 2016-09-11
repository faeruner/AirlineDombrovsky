package by.pvt.module3.command;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.login");
		return page;
	}
}