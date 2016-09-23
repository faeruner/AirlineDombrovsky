package by.pvt.module3.command.factory;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.EmptyCommand;
import by.pvt.module3.command.client.CommandEnum;
import by.pvt.module3.resource.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class ActionFactory {
    @Autowired
    EmptyCommand emptyCommand;

    public ActionCommand defineCommand(Map<String, String> paramMap, Model model) {
        ActionCommand current = emptyCommand;
        String action = paramMap.get("command");
        if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());

			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
            model.addAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
		return current;
	}
}