package by.pvt.module3.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.filter.UserType;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.resource.MessageManager;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		try {
			User user = null;
			if (login != null) {
				user = UserDAO.getInstance().getUserByLogin(login);
			}
			if (user != null && pass.equals(user.getPassword())) {
				request.setAttribute("user", user);
				HttpSession session = request.getSession(true);
				session.setAttribute("user_id", user.getId());
				if (UserType.ADMINISTRATOR.getId().equals(user.getRole().getId())) {
					session.setAttribute("userType", UserType.ADMINISTRATOR);
					page = ConfigurationManager.getProperty("path.page.main");
				} else if (UserType.DISPATCHER.getId().equals(user.getRole().getId())) {
					session.setAttribute("userType", UserType.DISPATCHER);
					page = ConfigurationManager.getProperty("path.page.user");
				} else {
					request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
					request.getSession().setAttribute("user_id", 0);
					page = ConfigurationManager.getProperty("path.page.login");
				}
			} else {
				request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
				request.getSession().setAttribute("user_id", 0);
				page = ConfigurationManager.getProperty("path.page.login");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return page;
	}
}
