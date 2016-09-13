package by.pvt.module3.command.user;

import by.pvt.module3.entity.User;

import javax.servlet.http.HttpServletRequest;

public class InsertUserCommand extends UserCommand {

    @Override
    public String execute(HttpServletRequest request) {
        User user = new User();
        updateEntity(user, request);
        getService().add(user);
        return getPage(request);
    }
}
