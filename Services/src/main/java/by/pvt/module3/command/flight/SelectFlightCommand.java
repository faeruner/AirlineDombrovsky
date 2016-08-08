package by.pvt.module3.command.flight;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.AirlineDAO;
import by.pvt.module3.dao.AirportDAO;
import by.pvt.module3.dao.CrewDAO;
import by.pvt.module3.dao.FlightDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.resource.ConfigurationManager;

public class SelectFlightCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			if (request.getParameter(Flight.ID) != null) {
				Flight flight = null;
				Integer id = Integer.parseInt(request.getParameter(Flight.ID).trim());
				if (id > 0) {
					flight = FlightDAO.getInstance().getFlight(id);

				} else {
					flight = new Flight();
					flight.setCreateDate(new Date());
					HttpSession session = request.getSession();
					Integer user_id = (Integer) session.getAttribute("user_id");
					flight.setUser(UserDAO.getInstance().getUser(user_id));
				}

				List<Airport> listDeparture = AirportDAO.getInstance().getAllAirports();
				request.setAttribute("departure", listDeparture);

				List<Airport> listArrival = AirportDAO.getInstance().getAllAirports();
				request.setAttribute("arrival", listArrival);

				List<Airline> listAirline = AirlineDAO.getInstance().getAllAirlines();
				request.setAttribute("by/pvt/module3/command/airline", listAirline);

				List<Crew> listCrew = CrewDAO.getInstance().getAllCrews();
				request.setAttribute("crew", listCrew);

				request.setAttribute("flight", flight);

				page = ConfigurationManager.getProperty("path.page.edit_flight");
			} else {
				List<Flight> list = FlightDAO.getInstance().getAllFlights();
				request.setAttribute("flight", list);
				page = ConfigurationManager.getProperty("path.page.flights");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return page;
	}
}
