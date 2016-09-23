package by.pvt.module3.command;

import by.pvt.module3.resource.ConfigurationManager;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class LogoutCommand implements ActionCommand {
	@Override
    public String execute(Map<String, String> paramMap, Model model) {
        String page = ConfigurationManager.getProperty("path.page.index");
//TODO:		request.getSession().invalidate();
        return page;
	}
}