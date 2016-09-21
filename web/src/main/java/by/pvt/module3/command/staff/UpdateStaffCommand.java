package by.pvt.module3.command.staff;

import by.pvt.module3.entity.Staff;

import javax.servlet.http.HttpServletRequest;

public class UpdateStaffCommand extends StaffCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Staff staff = getService().getById(Integer.parseInt(request.getParameter(ID).trim()));
        updateEntity(staff, request);
        return update(staff, request);
    }
}
