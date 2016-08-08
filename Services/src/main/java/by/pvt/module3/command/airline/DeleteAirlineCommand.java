package by.pvt.module3.command.airline;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.AirlineDAO;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.resource.ConfigurationManager;

public class DeleteAirlineCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            AirlineDAO.getInstance().deleteAirline(Integer.parseInt(request.getParameter(Airline.ID).trim()));
            
            List<Airline> list = AirlineDAO.getInstance().getAllAirlines();
            request.setAttribute("by/pvt/module3/command/airline", list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ConfigurationManager.getProperty("path.page.airlines");
    }

}
