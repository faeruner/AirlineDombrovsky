package by.pvt.module3.command.user;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.User;
import by.pvt.module3.entity.UserRole;
import by.pvt.module3.service.BaseService;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceUser;
import by.pvt.module3.service.ServiceUserRole;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by v on 08.09.2016.
 */
public class UserCommand extends BaseCommand<User> {

    private static final String LIST_USER_ROLE = "user_roles";

    public UserCommand() {
        super(new ServiceUser(), "path.page.edit_user", "path.page.users");
    }

    @Override
    protected void initEditAttributes(User user, HttpServletRequest request) {
        ServiceUserRole serviceUserRole = new ServiceUserRole();
        request.setAttribute(LIST_USER_ROLE, serviceUserRole.getAll());
    }
    protected void updateEntity(User user, HttpServletRequest request){
        user.setName(request.getParameter(User.NAME).trim());
        user.setSurname(request.getParameter(User.SURNAME).trim());
        user.setLogin(request.getParameter(User.LOGIN).trim());
        user.setPassword(request.getParameter(User.PASSWORD).trim());

        ServiceUserRole serviceUserRole = new ServiceUserRole();
        user.setRole(serviceUserRole.getById(Integer.parseInt(request.getParameter(User.USER_ROLE_ID).trim())));
    }
}
