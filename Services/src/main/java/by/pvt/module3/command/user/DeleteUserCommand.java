package by.pvt.module3.command.user;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.resource.ConfigurationManager;

public class DeleteUserCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		try {
			UserDAO.getInstance().deleteUser(Integer.parseInt(request.getParameter(User.ID).trim()));

			List<User> list = UserDAO.getInstance().getAllUsers();
			request.setAttribute("user", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.users");
	}
}
