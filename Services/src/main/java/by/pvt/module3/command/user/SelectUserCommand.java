package by.pvt.module3.command.user;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.dao.UserRoleDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.entity.UserRole;
import by.pvt.module3.resource.ConfigurationManager;

public class SelectUserCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			if (request.getParameter(User.ID) != null) {
				User user = new User();
				Integer id = Integer.parseInt(request.getParameter(User.ID).trim());
				if (id > 0) {
					user = UserDAO.getInstance().getUser(id);
					request.setAttribute("user", user);
				}
				List<UserRole> listRole = UserRoleDAO.getInstance().getAllUserRoles();
				request.setAttribute("user_roles", listRole);
				page = ConfigurationManager.getProperty("path.page.edit_user");
			} else {
				List<User> list = UserDAO.getInstance().getAllUsers();
				request.setAttribute("user", list);
				page = ConfigurationManager.getProperty("path.page.users");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return page;
	}
}
