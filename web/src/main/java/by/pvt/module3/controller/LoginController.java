package by.pvt.module3.controller;

import by.pvt.module3.controller.common.CommonController;
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

@Controller
public class LoginController {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Autowired
    private UserService userService;

    @Autowired
    private AirlineController airlineController;
    @Autowired
    private CrewController crewController;

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpSession httpSession) {
        String page = ConfigurationManager.getProperty("path.page.index");
        httpSession.invalidate();
        return page;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    private String login(@RequestParam Map<String, String> paramMap, Model model, HttpSession httpSession) {
        String page;
        String login = paramMap.get(PARAM_NAME_LOGIN);
        String pass = paramMap.get(PARAM_NAME_PASSWORD);
        User user = null;
        if (login != null) {
            user = userService.getUserByLogin(login);
        }
        if (user != null && pass.equals(user.getPassword())) {
            model.addAttribute("user", user);

            httpSession.setAttribute(CommonController.USER_ID, user.getId());

            if (UserType.ADMINISTRATOR.getId().equals(user.getRole().getId())) {
                httpSession.setAttribute("userType", UserType.ADMINISTRATOR);
                page = airlineController.perform(paramMap, model);
            } else if (UserType.DISPATCHER.getId().equals(user.getRole().getId())) {
                httpSession.setAttribute("userType", UserType.DISPATCHER);
                page = crewController.perform(paramMap, model, httpSession);
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
