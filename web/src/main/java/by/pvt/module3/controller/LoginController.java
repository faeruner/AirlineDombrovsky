package by.pvt.module3.controller;

import by.pvt.module3.command.client.CommandEnum;
import by.pvt.module3.command.factory.ActionFactory;
import by.pvt.module3.controller.common.ControllerUtils;
import by.pvt.module3.entity.User;
import by.pvt.module3.filter.UserType;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.resource.MessageManager;
import by.pvt.module3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by asd on 26.09.2016.
 */
@Controller
@RequestMapping(value = "login", method = RequestMethod.POST)
public class LoginController {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Autowired
    UserService userService;

    @Autowired
    ActionFactory client;

    @Autowired
    AirlineController airlineController;

    @RequestMapping
    private String loginUser(@RequestParam Map<String, String> paramMap, Model model, HttpSession httpSession) {
        String page;
        String login = paramMap.get(PARAM_NAME_LOGIN);
        String pass = paramMap.get(PARAM_NAME_PASSWORD);
        User user = null;
        if (login != null) {
            user = userService.getUserByLogin(login);
        }
        if (user != null && pass.equals(user.getPassword())) {
            model.addAttribute("user", user);

            httpSession.setAttribute(ControllerUtils.USER_ID, user.getId());

            if (UserType.ADMINISTRATOR.getId().equals(user.getRole().getId())) {
                httpSession.setAttribute("userType", UserType.ADMINISTRATOR);
                paramMap.put(ActionFactory.PARAM_COMMAND, CommandEnum.SEL_AIRLINE.name());
                page = airlineController.showList(paramMap, model);
            } else if (UserType.DISPATCHER.getId().equals(user.getRole().getId())) {
                httpSession.setAttribute("userType", UserType.DISPATCHER);
                page = airlineController.showList(paramMap, model);
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
