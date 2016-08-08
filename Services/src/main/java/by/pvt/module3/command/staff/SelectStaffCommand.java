package by.pvt.module3.command.staff;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.MemberTypeDAO;
import by.pvt.module3.dao.StaffDAO;
import by.pvt.module3.entity.MemberType;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;

public class SelectStaffCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {

			if (request.getParameter(Staff.ID) != null) {
				Staff staff = new Staff();
				Integer id = Integer.parseInt(request.getParameter(Staff.ID).trim());
				if (id > 0) {
					staff = StaffDAO.getInstance().getStaff(id);
					request.setAttribute("staff", staff);
				}
				List<MemberType> listMemberType = MemberTypeDAO.getInstance().getAllMemberTypes();
				request.setAttribute("member_type", listMemberType);
				page = ConfigurationManager.getProperty("path.page.edit_staff");
			} else {
				List<Staff> list = StaffDAO.getInstance().getAllStaff();
				request.setAttribute("staff", list);
				page = ConfigurationManager.getProperty("path.page.staff");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return page;
	}
}
