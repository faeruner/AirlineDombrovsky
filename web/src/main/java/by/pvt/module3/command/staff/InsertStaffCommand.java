package by.pvt.module3.command.staff;

import by.pvt.module3.entity.Staff;

import javax.servlet.http.HttpServletRequest;

public class InsertStaffCommand extends StaffCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Staff staff = new Staff();
        updateEntity(staff, request);
        getService().add(staff);
        return getPage(request);
    }
}
