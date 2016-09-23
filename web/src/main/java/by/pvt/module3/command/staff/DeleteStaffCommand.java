package by.pvt.module3.command.staff;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class DeleteStaffCommand extends StaffCommand{

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        return delete(paramMap, model);
    }
}
