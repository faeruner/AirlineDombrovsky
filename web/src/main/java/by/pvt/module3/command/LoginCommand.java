package by.pvt.module3.command;

import by.pvt.module3.command.client.CommandEnum;
import by.pvt.module3.command.factory.ActionFactory;
import by.pvt.module3.entity.User;
import by.pvt.module3.filter.UserType;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.resource.MessageManager;
import by.pvt.module3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Autowired
    UserService userService;

    @Autowired
    ActionFactory client;

    @Override
    public String execute(Map<String, String> paramMap, Model model, HttpSession httpSession) {
        String page;
        String login = paramMap.get(PARAM_NAME_LOGIN);
        String pass = paramMap.get(PARAM_NAME_PASSWORD);
        User user = null;
        if (login != null) {
            user = userService.getUserByLogin(login);
        }
        if (user != null && pass.equals(user.getPassword())) {
            model.addAttribute("user", user);

            httpSession.setAttribute(BaseCommand.USER_ID, user.getId());

            if (UserType.ADMINISTRATOR.getId().equals(user.getRole().getId())) {
                httpSession.setAttribute("userType", UserType.ADMINISTRATOR);
                paramMap.put(ActionFactory.PARAM_COMMAND, CommandEnum.SEL_AIRLINE.name());
                page = client.defineCommand(paramMap, model).execute(paramMap, model, httpSession);
            } else if (UserType.DISPATCHER.getId().equals(user.getRole().getId())) {
                httpSession.setAttribute("userType", UserType.DISPATCHER);
                paramMap.put(ActionFactory.PARAM_COMMAND, CommandEnum.SEL_CREW.name());
                page = client.defineCommand(paramMap, model).execute(paramMap, model, httpSession);
            } else {
                model.addAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                httpSession.setAttribute("user_id", 0);
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } else {
            model.addAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            httpSession.setAttribute("user_id", 0);
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
