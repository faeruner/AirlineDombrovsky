package by.pvt.module3.command.user;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand extends UserCommand {

    @Override
    public String execute(HttpServletRequest request) {
        getService().delete(Integer.parseInt(request.getParameter(ID).trim()));
        return getPage(request);
    }
}
