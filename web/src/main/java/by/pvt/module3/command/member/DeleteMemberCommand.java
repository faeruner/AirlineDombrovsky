package by.pvt.module3.command.member;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.crew.SelectCrewCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceCrew;
import by.pvt.module3.service.ServiceMemberType;
import by.pvt.module3.service.ServiceStaff;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteMemberCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        CommonService serviceCrew = new ServiceCrew();
        Crew crew = (Crew)serviceCrew.getById(Integer.parseInt(request.getParameter(Crew.ID).trim()));
        Integer staff_id = Integer.parseInt(request.getParameter(Crew.STAFF_ID).trim());

        for (Staff staff : crew.getMembers()) {
            if (staff.getId().equals(staff_id)) {
                crew.getMembers().remove(staff);
                break;
            }
        }
        serviceCrew.update(crew);

        return (new SelectCrewCommand()).execute(request);

//        CommonService serviceStaff = new ServiceStaff();
//        List<Staff> staffs = serviceStaff.getAll();
//        for (Staff member : crew.getMembers()) {
//            staffs.remove(member);
//        }
//        String readyNo = "checked";
//        String readyYes = "";
//
//        if (crew.getReady() > 0) {
//            readyNo = "";
//            readyYes = "checked";
//        }
//        request.setAttribute("staff", staffs);
//        request.setAttribute("readyNo", readyNo);
//        request.setAttribute("readyYes", readyYes);
//        request.setAttribute("crew", crew);
//
//        return ConfigurationManager.getProperty("path.page.edit_crew");
    }

}
