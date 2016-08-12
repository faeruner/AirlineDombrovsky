package by.pvt.module3.command.airport;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.AirportDAO;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.resource.ConfigurationManager;

public class DeleteAirportCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		try {
			AirportDAO.getInstance().deleteAirport(Integer.parseInt(request.getParameter(Airport.ID).trim()));
			
			List<Airport> list = AirportDAO.getInstance().getAllAirports();
			request.setAttribute("airport", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.airports");
	}
}
