package by.pvt.module3.command.airport;

import java.sql.SQLException;
import java.util.ArrayList;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SelectAirportCommand extends AirportCommand {

    public String execute(HttpServletRequest request) {
        String page = null;
        CommonService serviceAirport = new ServiceAirport();
        if (request.getParameter(Airport.ID) != null) {
            Integer id = Integer.parseInt(request.getParameter(Airport.ID).trim());
            if (id > 0) {
                request.setAttribute("airport", serviceAirport.getById(id));
            }
            page = ConfigurationManager.getProperty("path.page.edit_airport");
        } else {
            page = getPage(request, serviceAirport);
        }
        return page;
    }
}