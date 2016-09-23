package by.pvt.module3.command.staff;

import by.pvt.module3.entity.Staff;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class UpdateStaffCommand extends StaffCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        Staff staff = getService().getById(Staff.class, Integer.parseInt(paramMap.get(ID).trim()));
        updateEntity(staff, paramMap);
        return update(staff, paramMap, model);
    }
}
