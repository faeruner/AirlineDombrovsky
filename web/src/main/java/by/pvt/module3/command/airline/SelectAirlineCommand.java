package by.pvt.module3.command.airline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceAirline;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SelectAirlineCommand extends AirlineCommand {

    public String execute(HttpServletRequest request) {
        String page = null;
        CommonService serviceAirline = new ServiceAirline();
        if (request.getParameter(Airline.ID) != null) {
            Integer id = Integer.parseInt(request.getParameter(Airline.ID).trim());
            if (id > 0) {
                request.setAttribute("airline", serviceAirline.getById(id));
            }
            page = ConfigurationManager.getProperty("path.page.edit_airline");
        } else {
            page = getPage(request, serviceAirline);
        }
        return page;
    }
}
