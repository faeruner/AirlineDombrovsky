package by.pvt.module3.command.flight;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.FlightDAO;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.resource.ConfigurationManager;

public class DeleteFlightCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		try {
			FlightDAO.getInstance().deleteFlight(Integer.parseInt(request.getParameter(Flight.ID).trim()));

			List<Flight> list = FlightDAO.getInstance().getAllFlights();
			request.setAttribute("flight", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.flights");
	}
}
