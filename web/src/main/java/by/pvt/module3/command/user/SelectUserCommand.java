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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SelectUserCommand extends UserCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        CommonService serviceUser = new ServiceUser();
        if (request.getParameter(User.ID) != null) {
            Integer id = Integer.parseInt(request.getParameter(User.ID).trim());
            if (id > 0) {
                request.setAttribute("user", serviceUser.getById(id));
            }

            CommonService serviceUserRole = new ServiceUserRole();
            List<UserRole> listRole = serviceUserRole.getAll();
            request.setAttribute("user_roles", listRole);
            page = ConfigurationManager.getProperty("path.page.edit_user");
        } else {
            page = getPage(request, serviceUser);
        }
        return page;
    }
}
