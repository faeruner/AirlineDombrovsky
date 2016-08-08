package by.pvt.module3.command;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.resource.ConfigurationManager;

public class DispCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		page = ConfigurationManager.getProperty("path.page.user");
		return page;

	}

}
