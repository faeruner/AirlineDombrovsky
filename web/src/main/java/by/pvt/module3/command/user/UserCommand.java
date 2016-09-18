package by.pvt.module3.command.user;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.User;
import by.pvt.module3.service.UserRoleService;
import by.pvt.module3.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by v on 08.09.2016.
 */
public class UserCommand extends BaseCommand<User> {

    private static final String LIST_USER_ROLE = "user_roles";

    public UserCommand() {
        super(new UserService(), "path.page.edit_user", "path.page.users");
    }

    @Override
    protected void initEditAttributes(User user, HttpServletRequest request) {
        UserRoleService userRoleService = new UserRoleService();
        request.setAttribute(LIST_USER_ROLE, userRoleService.getAll());
    }
    protected void updateEntity(User user, HttpServletRequest request){
        user.setName(request.getParameter(User.NAME).trim());
        user.setSurname(request.getParameter(User.SURNAME).trim());
        user.setLogin(request.getParameter(User.LOGIN).trim());
        user.setPassword(request.getParameter(User.PASSWORD).trim());

        UserRoleService userRoleService = new UserRoleService();
        user.setRole(userRoleService.getById(Integer.parseInt(request.getParameter(User.USER_ROLE_ID).trim())));
    }
}
