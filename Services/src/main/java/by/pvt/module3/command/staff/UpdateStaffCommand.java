package by.pvt.module3.command.staff;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.MemberTypeDAO;
import by.pvt.module3.dao.StaffDAO;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;

public class UpdateStaffCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		Staff staff = new Staff();
		try {
			staff.setId(Integer.parseInt(request.getParameter(Staff.ID).trim()));
			staff.setName(request.getParameter(Staff.NAME).trim());
			staff.setSurname(request.getParameter(Staff.SURNAME).trim());
			staff.setMember(MemberTypeDAO.getInstance()
					.getMemberType(Integer.parseInt(request.getParameter(Staff.MEMBER_TYPE_ID).trim())));

			StaffDAO.getInstance().updateStaff(staff);
			List<Staff> list = StaffDAO.getInstance().getAllStaff();
			request.setAttribute("staff", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.staff");
	}
}
