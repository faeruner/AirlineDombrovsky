package by.pvt.module3.command.user;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.entity.UserRole;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdateUserCommand extends UserCommand {

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

        CommonService serviceUser = new ServiceUser();
        serviceUser.update(user);

        return getPage(request, serviceUser);
    }
}
