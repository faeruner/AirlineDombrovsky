package by.pvt.module3.servlet;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.command.client.CommandEnum;
import by.pvt.module3.command.factory.ActionFactory;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.resource.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class BaseController {
    private static final long serialVersionUID = 2947647124621648551L;

    @Autowired
    ActionFactory client;

/*
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    private String processIndex(@RequestParam Map<String, String> paramMap, Model model, HttpSession httpSession) {
        return ConfigurationManager.getProperty("path.page.index");
    }
*/

//    @RequestMapping(value = "/loginA", method = {RequestMethod.POST})
//    private String processLogin(@RequestParam Map<String, String> paramMap, Model model, HttpSession httpSession, HttpServletResponse response) {
//        return processRequest(paramMap, model, httpSession);
//    }

    @RequestMapping(value = "/controller", method = {RequestMethod.POST, RequestMethod.GET})
    private String processController(@RequestParam Map<String, String> paramMap, Model model
            , HttpSession httpSession, HttpServletResponse response) {
        if (CommandEnum.SEL_AIRLINE.name().equals(paramMap.get(ActionFactory.PARAM_COMMAND).toUpperCase())) {
            try {
                response.sendRedirect("/controller/airline/list");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ConfigurationManager.getProperty("path.page.airlines");
        }
        return processRequest(paramMap, model, httpSession);
    }

    private String processRequest(Map<String, String> paramMap, Model model, HttpSession httpSession) {
        ActionCommand command = client.defineCommand(paramMap, model);
        model.addAttribute(BaseCommand.USER_ID, httpSession.getAttribute(BaseCommand.USER_ID));
        String page = command.execute(paramMap, model, httpSession);
        if (page == null) {
            page = ConfigurationManager.getProperty("path.page.index");
            httpSession.setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
        }
        return page;
    }
}