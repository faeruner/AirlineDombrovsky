package by.pvt.module3.command.user;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class DeleteUserCommand extends UserCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        return delete(paramMap, model);
    }
}
