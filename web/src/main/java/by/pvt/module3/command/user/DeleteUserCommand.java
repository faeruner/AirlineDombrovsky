package by.pvt.module3.command.user;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand extends UserCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return delete(request);
    }
}
