package by.pvt.module3.command.airport;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceAirport;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteAirportCommand extends AirportCommand {

    public String execute(HttpServletRequest request) {
        CommonService serviceAirport = new ServiceAirport();
        serviceAirport.delete(Integer.parseInt(request.getParameter(Airport.ID).trim()));

        return getPage(request, serviceAirport);
    }
}
