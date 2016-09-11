package by.pvt.module3.command.staff;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.MemberType;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceMemberType;
import by.pvt.module3.service.ServiceStaff;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SelectStaffCommand extends StaffCommand  {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        CommonService serviceStaff = new ServiceStaff();
        ServiceMemberType serviceMemberType = new ServiceMemberType();
        if (request.getParameter(Staff.ID) != null) {
            Integer id = Integer.parseInt(request.getParameter(Staff.ID).trim());
            if (id > 0) {
                request.setAttribute("staff", serviceStaff.getById(id));
                request.setAttribute("member_type", serviceMemberType.getAll());
            }
            page = ConfigurationManager.getProperty("path.page.edit_staff");
        } else {
            page = getPage(request, serviceStaff);
        }
        return page;
    }
}
