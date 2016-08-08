package by.pvt.module3.command.user;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.entity.UserRole;
import by.pvt.module3.resource.ConfigurationManager;

public class UpdateUserCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		User user = new User();
		user.setId(Integer.parseInt(request.getParameter(User.ID).trim()));
		user.setName(request.getParameter(User.NAME).trim());
		user.setSurname(request.getParameter(User.SURNAME).trim());
		user.setLogin(request.getParameter(User.LOGIN).trim());
		user.setPassword(request.getParameter(User.PASSWORD).trim());
		user.setRole(new UserRole());
		user.getRole().setId(Integer.parseInt(request.getParameter(User.USER_ROLE_ID).trim()));
		try {
			UserDAO.getInstance().updateUser(user);

			List<User> list = UserDAO.getInstance().getAllUsers();
			request.setAttribute("user", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.users");
	}
}
