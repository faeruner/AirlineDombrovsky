package by.pvt.module3.command.user;

import by.pvt.module3.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class InsertUserCommand extends UserCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        User user = new User();
        updateEntity(user, paramMap);
        return insert(user, paramMap, model);
    }
}
