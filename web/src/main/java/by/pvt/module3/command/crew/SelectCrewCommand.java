package by.pvt.module3.command.crew;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.entity.User;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceCrew;
import by.pvt.module3.service.ServiceStaff;
import by.pvt.module3.service.ServiceUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SelectCrewCommand extends CrewCommand {

    public String execute(HttpServletRequest request) {
        String page = null;
        Crew crew = null;
        CommonService serviceCrew = new ServiceCrew();

        if (request.getParameter(Crew.ID) != null) {
            Integer id = Integer.parseInt(request.getParameter(Crew.ID).trim());
            if (id > 0) {
                crew = (Crew)serviceCrew.getById(id);
            }
            else
            {
                crew = new Crew();
                crew.setReady(0);
                crew.setCreateDate(new Date());
                crew.setMembers(new HashSet<Staff>());

                HttpSession sessionHttp = request.getSession();
                Integer user_id = (Integer) sessionHttp.getAttribute("user_id");
                CommonService serviceUser = new ServiceUser();
                crew.setUser((User)serviceUser.getById(user_id));
            }

            CommonService serviceStaff = new ServiceStaff();
            List<Staff> staffs = serviceStaff.getAll();
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

            page = ConfigurationManager.getProperty("path.page.edit_crew");
        } else {
            page = getPage(request, serviceCrew);
        }
        return page;
    }
}
