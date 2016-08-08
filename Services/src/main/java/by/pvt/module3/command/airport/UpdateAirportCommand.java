package by.pvt.module3.command.airport;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.AirportDAO;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.resource.ConfigurationManager;

public class UpdateAirportCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		Airport air = new Airport();
		air.setId(Integer.parseInt(request.getParameter(Airport.ID).trim()));
		air.setName(request.getParameter(Airport.NAME).trim());
		
		try {
			AirportDAO.getInstance().updateAirport(air);

			List<Airport> list = AirportDAO.getInstance().getAllAirports();
			request.setAttribute("by/pvt/module3/command/airport", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.airports");
	}
}
