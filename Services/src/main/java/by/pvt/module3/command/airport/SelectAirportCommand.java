package by.pvt.module3.command.airport;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.AirportDAO;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.resource.ConfigurationManager;

public class SelectAirportCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			if (request.getParameter(Airport.ID) != null) {
				Airport air = new Airport();
				Integer id = Integer.parseInt(request.getParameter(Airport.ID).trim());
				if (id > 0) {
					air.setId(id);
					air = AirportDAO.getInstance().getAirport(air.getId());
					request.setAttribute("by/pvt/module3/command/airport", air);
				}
				page = ConfigurationManager.getProperty("path.page.edit_airport");
			} else {
				List<Airport> list = AirportDAO.getInstance().getAllAirports();
				request.setAttribute("by/pvt/module3/command/airport", list);
				page = ConfigurationManager.getProperty("path.page.airports");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return page;
	}
}
