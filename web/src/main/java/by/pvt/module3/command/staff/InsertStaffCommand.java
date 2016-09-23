package by.pvt.module3.command.staff;

import by.pvt.module3.entity.Staff;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class InsertStaffCommand extends StaffCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        Staff staff = new Staff();
        updateEntity(staff, paramMap);
        return insert(staff, paramMap, model);
    }
}
