package by.pvt.module3.command;

import by.pvt.module3.command.airline.AirlineCommand;
import by.pvt.module3.command.crew.CrewCommand;
import by.pvt.module3.entity.User;
import by.pvt.module3.filter.UserType;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.resource.MessageManager;
import by.pvt.module3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        User user = null;
        if (login != null) {
            UserService userService = new UserService();
            user = userService.getUserByLogin(login);
        }
        if (user != null && pass.equals(user.getPassword())) {
            request.setAttribute("user", user);
            HttpSession session = request.getSession(true);
            session.setAttribute("user_id", user.getId());
            if (UserType.ADMINISTRATOR.getId().equals(user.getRole().getId())) {
                session.setAttribute("userType", UserType.ADMINISTRATOR);
                page = (new AirlineCommand()).getPage(request);
            } else if (UserType.DISPATCHER.getId().equals(user.getRole().getId())) {
                session.setAttribute("userType", UserType.DISPATCHER);
                page = (new CrewCommand()).getPage(request);
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                request.getSession().setAttribute("user_id", 0);
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            request.getSession().setAttribute("user_id", 0);
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
