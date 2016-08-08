package by.pvt.module3.command.crew;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CrewDAO;
import by.pvt.module3.dao.StaffDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;

public class SelectCrewCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            if (request.getParameter(Crew.ID) != null) {
                Crew crew = null;
                Integer id = Integer.parseInt(request.getParameter(Crew.ID).trim());

                if (id > 0) {
                    crew = CrewDAO.getInstance().getCrew(id);
                    request.setAttribute("crew", crew);
                } else {
                    crew = new Crew();
                    crew.setReady(0);
                    crew.setCreateDate(new Date());
                    crew.setMembers(new ArrayList<Staff>());
                    
                    HttpSession session = request.getSession();
                    Integer user_id = (Integer)session.getAttribute("user_id");
                    crew.setUser(UserDAO.getInstance().getUser(user_id));
                }

    	        List<Staff> staffs = StaffDAO.getInstance().getAllStaff();
    	        for(Staff member: crew.getMembers()){
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
                page = ConfigurationManager.getProperty("path.page.edit_crew");
            } else {
                List<Crew> list = CrewDAO.getInstance().getAllCrews();
                request.setAttribute("crew", list);
                page = ConfigurationManager.getProperty("path.page.crews");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return page;
    }
}
