package by.pvt.module3.command.user;

import by.pvt.module3.entity.User;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand extends UserCommand {

    @Override
    public String execute(HttpServletRequest request) {
        User user = getService().getById(Integer.parseInt(request.getParameter(ID).trim()));
        updateEntity(user, request);
        return update(user, request);
    }
}
