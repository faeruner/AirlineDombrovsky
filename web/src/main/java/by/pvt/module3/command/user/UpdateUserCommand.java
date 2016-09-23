package by.pvt.module3.command.user;

import by.pvt.module3.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class UpdateUserCommand extends UserCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        User user = getService().getById(User.class, Integer.parseInt(paramMap.get(ID).trim()));
        updateEntity(user, paramMap);
        return update(user, paramMap, model);
    }
}
