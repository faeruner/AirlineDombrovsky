package by.pvt.module3.command.staff;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.StaffDAO;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;

public class DeleteStaffCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		try {
			StaffDAO.getInstance().deleteStaff(Integer.parseInt(request.getParameter(Staff.ID).trim()));
			List<Staff> list = StaffDAO.getInstance().getAllStaff();
			request.setAttribute("staff", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.staff");
	}
}
