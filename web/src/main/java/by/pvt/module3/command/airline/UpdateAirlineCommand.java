package by.pvt.module3.command.airline;

import java.sql.SQLException;
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

public class UpdateAirlineCommand extends AirlineCommand {

    public String execute(HttpServletRequest request) {
            CommonService serviceAirline = new ServiceAirline();
            serviceAirline.update(new Airline(Integer.parseInt(request.getParameter(Airline.ID).trim()),
                    request.getParameter(Airline.NAME).trim()));

        return getPage(request, serviceAirline);
    }
}
