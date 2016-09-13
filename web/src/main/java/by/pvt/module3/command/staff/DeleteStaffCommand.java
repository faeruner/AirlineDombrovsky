package by.pvt.module3.command.staff;

import javax.servlet.http.HttpServletRequest;

public class DeleteStaffCommand extends StaffCommand{

    @Override
    public String execute(HttpServletRequest request) {
        getService().delete(Integer.parseInt(request.getParameter(ID).trim()));
        return getPage(request);
    }
}
