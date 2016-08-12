package by.pvt.module3.command.airport;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.AirportDAO;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.resource.ConfigurationManager;

public class InsertAirportCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		Airport air = new Airport();
		air.setName(request.getParameter(Airport.NAME).trim());
		try {
			AirportDAO.getInstance().addAirport(air);

			List<Airport> list = AirportDAO.getInstance().getAllAirports();
			request.setAttribute("airport", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.airports");
	}
}
