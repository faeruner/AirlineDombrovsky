package by.pvt.module3.command.staff;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceStaff;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteStaffCommand extends StaffCommand{

    @Override
    public String execute(HttpServletRequest request) {
        CommonService serviceStaff = new ServiceStaff();
        serviceStaff.delete(Integer.parseInt(request.getParameter(Staff.ID).trim()));

        return getPage(request, serviceStaff);
    }
}
