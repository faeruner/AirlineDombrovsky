package by.pvt.module3.command.member;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CrewDAO;
import by.pvt.module3.dao.StaffDAO;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;

public class DeleteMemberCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Crew crew = CrewDAO.getInstance().getCrew(Integer.parseInt(request.getParameter(Crew.CREW_ID).trim()));
			Integer staff_id = Integer.parseInt(request.getParameter(Crew.STAFF_ID).trim());

			for (Staff staff : crew.getMembers()) {
				if (staff.getId().equals(staff_id)) {
					crew.getMembers().remove(staff);
					break;
				}
			}
			CrewDAO.getInstance().updateCrew(crew);

			List<Staff> staffs = StaffDAO.getInstance().getAllStaff();
			for (Staff member : crew.getMembers()) {
				staffs.remove(member);
			}
			String readyNo = "checked";
			String readyYes = "";

			if (crew.getReady() > 0) {
				readyNo = "";
				readyYes = "checked";
			}
			request.setAttribute("staff", staffs);
			request.setAttribute("readyNo", readyNo);
			request.setAttribute("readyYes", readyYes);
			request.setAttribute("crew", crew);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ConfigurationManager.getProperty("path.page.edit_crew");
	}

}
