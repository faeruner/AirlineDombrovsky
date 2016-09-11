package by.pvt.module3.command.flight;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.*;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SelectFlightCommand extends FlightCommand {

    public String execute(HttpServletRequest request) {
        String page = null;
        CommonService serviceFlight = new ServiceFlight();
        if (request.getParameter(Flight.ID) != null) {
            Flight flight = null;
            Integer id = Integer.parseInt(request.getParameter(Flight.ID).trim());
            if (id > 0) {
                flight = (Flight) serviceFlight.getById(id);
            }else {
                flight = new Flight();
                flight.setCreateDate(new Date());
                HttpSession sessionHttp = request.getSession();
                Integer user_id = (Integer) sessionHttp.getAttribute("user_id");
                CommonService serviceUser = new ServiceUser();
                flight.setUser((User)serviceUser.getById(user_id));
            }

            CommonService serviceAirport = new ServiceAirport();
            List<Airport> listAirports = serviceAirport.getAll();
            request.setAttribute("departure", listAirports);
            request.setAttribute("arrival", listAirports);

            CommonService serviceAirline = new ServiceAirline();
            List<Airline> listAirline = serviceAirline.getAll();
            request.setAttribute("airline", listAirline);

            CommonService serviceCrew = new ServiceCrew();
            List<Crew> listCrew = serviceCrew.getAll();
            request.setAttribute("crew", listCrew);

            request.setAttribute("flight", flight);

            page = ConfigurationManager.getProperty("path.page.edit_flight");
        } else {
            page = getPage(request, serviceFlight);
        }
        return page;
    }
}
