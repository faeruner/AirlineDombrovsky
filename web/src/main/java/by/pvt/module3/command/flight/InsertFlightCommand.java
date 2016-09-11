package by.pvt.module3.command.flight;

import java.sql.SQLException;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class InsertFlightCommand extends FlightCommand {

    private static final DateFormat DF = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public String execute(HttpServletRequest request) {

        Flight flight = new Flight();
        flight.setCode(request.getParameter(Flight.CODE).trim());

        try {
            flight.setDepDate(DF.parse(request.getParameter(Flight.DEPARTURE_DATE).trim()));
            flight.setReturnDate(DF.parse(request.getParameter(Flight.RETURN_DATE).trim()));
            flight.setCreateDate(DF.parse(request.getParameter(Flight.CREATE_DATE).trim()));
        } catch (ParseException e) {
            log.error(e);
        }

        CommonService serviceAirport = new ServiceAirport();
        CommonService serviceAirline = new ServiceAirline();
        CommonService serviceCrew = new ServiceCrew();

        flight.setArrival((Airport) serviceAirport.
                getById(Integer.parseInt(request.getParameter(Flight.AIRPORT_ARV_ID).trim())));
        flight.setDeparture((Airport) serviceAirport.
                getById(Integer.parseInt(request.getParameter(Flight.AIRPORT_DEP_ID).trim())));
        flight.setAirline((Airline) serviceAirline.
                getById(Integer.parseInt(request.getParameter(Flight.AIRLINE_ID).trim())));
        flight.setCrew((Crew) serviceCrew.
                getById(Integer.parseInt(request.getParameter(Flight.CREW_ID).trim())));

        HttpSession sessionHttp = request.getSession();
        Integer user_id = (Integer) sessionHttp.getAttribute("user_id");

        CommonService serviceUser = new ServiceUser();
        flight.setUser((User)serviceUser.getById(user_id));

        CommonService serviceFlight = new ServiceFlight();
        serviceFlight.add(flight);

        return getPage(request, serviceFlight);
    }
}
