package by.pvt.module3.command.user;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.User;
import by.pvt.module3.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

/**
 * Created by v on 08.09.2016.
 */
@Component
public class UserCommand extends BaseCommand<User> {

    private static final String LIST_USER_ROLE = "user_roles";

    public UserCommand() {
        super(User.class, "path.page.edit_user", "path.page.users");
    }

    @Autowired
    UserRoleService userRoleService;

    @Override
    protected void initEditAttributes(User user, Model model) {
        model.addAttribute(LIST_USER_ROLE, userRoleService.getAll());
    }

    protected void updateEntity(User user, Map<String, String> paramMap) {
        user.setName(paramMap.get(User.NAME).trim());
        user.setSurname(paramMap.get(User.SURNAME).trim());
        user.setLogin(paramMap.get(User.LOGIN).trim());
        user.setPassword(paramMap.get(User.PASSWORD).trim());
        user.setRole(userRoleService.getById(Integer.parseInt(paramMap.get(User.USER_ROLE_ID).trim())));
    }
}
