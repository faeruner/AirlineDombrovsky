package by.pvt.module3.command;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.resource.ConfigurationManager;

public class AdminCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		page = ConfigurationManager.getProperty("path.page.main");
		return page;

	}

}
