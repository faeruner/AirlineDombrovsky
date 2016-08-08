package by.pvt.module3.command.flight;

import java.sql.SQLException;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.AirlineDAO;
import by.pvt.module3.dao.AirportDAO;
import by.pvt.module3.dao.CrewDAO;
import by.pvt.module3.dao.FlightDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.resource.ConfigurationManager;

public class InsertFlightCommand implements ActionCommand {

	private static final DateFormat DF = new SimpleDateFormat("dd.MM.yyyy");

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Flight flight = new Flight();
			flight.setCode(request.getParameter(Flight.CODE).trim());
			flight.setDepDate(DF.parse(request.getParameter(Flight.DEPARTURE_DATE).trim()));
			flight.setReturnDate(DF.parse(request.getParameter(Flight.RETURN_DATE).trim()));
			flight.setCreateDate(DF.parse(request.getParameter(Flight.CREATE_DATE).trim()));
			flight.setArrival(AirportDAO.getInstance()
					.getAirport(Integer.parseInt(request.getParameter(Flight.AIRPORT_ARV_ID).trim())));
			flight.setDeparture(AirportDAO.getInstance()
					.getAirport(Integer.parseInt(request.getParameter(Flight.AIRPORT_DEP_ID).trim())));
			flight.setAirline(AirlineDAO.getInstance()
					.getAirline(Integer.parseInt(request.getParameter(Flight.AIRLINE_ID).trim())));
			flight.setCrew(
					CrewDAO.getInstance().getCrew(Integer.parseInt(request.getParameter(Flight.CREW_ID).trim())));

			HttpSession session = request.getSession();
			Integer user_id = (Integer) session.getAttribute("user_id");
			flight.setUser(UserDAO.getInstance().getUser(user_id));

			FlightDAO.getInstance().addFlight(flight);

			List<Flight> list = FlightDAO.getInstance().getAllFlights();
			request.setAttribute("flight", list);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.flights");
	}
}
