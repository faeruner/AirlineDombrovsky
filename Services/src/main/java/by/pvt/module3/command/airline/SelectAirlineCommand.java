package by.pvt.module3.command.airline;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.AirlineDAO;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.resource.ConfigurationManager;

public class SelectAirlineCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            if (request.getParameter(Airline.ID) != null) {
                Integer id = Integer.parseInt(request.getParameter(Airline.ID).trim());
                if (id > 0) {
                    Airline air = AirlineDAO.getInstance().getAirline(id);
                    request.setAttribute("airline", air);
                }
                page = ConfigurationManager.getProperty("path.page.edit_airline");
            } else {
                List<Airline> list = AirlineDAO.getInstance().getAllAirlines();
                request.setAttribute("airline", list);
                page = ConfigurationManager.getProperty("path.page.airlines");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return page;
    }
}
