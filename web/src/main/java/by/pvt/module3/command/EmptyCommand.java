package by.pvt.module3.command;

import by.pvt.module3.resource.ConfigurationManager;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class EmptyCommand implements ActionCommand {

	@Override
    public String execute(Map<String, String> paramMap, Model model, HttpSession httpSession) {
        return ConfigurationManager.getProperty("path.page.login");
	}
}