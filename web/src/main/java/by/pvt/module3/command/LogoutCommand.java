package by.pvt.module3.command;

import by.pvt.module3.resource.ConfigurationManager;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class LogoutCommand implements ActionCommand {
	@Override
    public String execute(Map<String, String> paramMap, Model model, HttpSession httpSession) {
        String page = ConfigurationManager.getProperty("path.page.index");
        httpSession.invalidate();
        return page;
	}
}