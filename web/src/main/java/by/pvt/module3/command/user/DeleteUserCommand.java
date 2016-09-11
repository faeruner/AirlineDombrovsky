package by.pvt.module3.command.user;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteUserCommand extends UserCommand {

    @Override
    public String execute(HttpServletRequest request) {
        CommonService serviceUser = new ServiceUser();
        serviceUser.delete(Integer.parseInt(request.getParameter(User.ID).trim()));

        return getPage(request, serviceUser);
    }
}
