package by.pvt.module3.command.user;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.entity.UserRole;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceUser;
import by.pvt.module3.service.ServiceUserRole;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class InsertUserCommand extends UserCommand {

    @Override
    public String execute(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter(User.NAME).trim());
        user.setSurname(request.getParameter(User.SURNAME).trim());
        user.setLogin(request.getParameter(User.LOGIN).trim());
        user.setPassword(request.getParameter(User.PASSWORD).trim());

        CommonService serviceUserRole = new ServiceUserRole();
        user.setRole((UserRole) serviceUserRole
                .getById(Integer.parseInt(request.getParameter(User.USER_ROLE_ID).trim())));

        CommonService serviceUser = new ServiceUser();
        serviceUser.add(user);

        return getPage(request, serviceUser);
    }
}
