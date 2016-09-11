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

public class UpdateStaffCommand extends StaffCommand {

    @Override
    public String execute(HttpServletRequest request) {
        CommonService serviceStaff = new ServiceStaff();
        ServiceMemberType serviceMemberType = new ServiceMemberType();
        Staff staff = new Staff();
        staff.setName(request.getParameter(Staff.NAME).trim());
        staff.setSurname(request.getParameter(Staff.SURNAME).trim());
        staff.setMember((MemberType) serviceMemberType.getById(Integer.parseInt(request.getParameter(Staff.MEMBER_TYPE_ID).trim())));
        serviceStaff.update(staff);

        return getPage(request, serviceStaff);
    }
}
