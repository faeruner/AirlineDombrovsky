package by.pvt.module3.command.airline;

import by.pvt.module3.entity.Airline;

import javax.servlet.http.HttpServletRequest;

public class UpdateAirlineCommand extends AirlineCommand {

    public String execute(HttpServletRequest request) {
        getService().update(new Airline(Integer.parseInt(request.getParameter(Airline.ID).trim()),
                request.getParameter(Airline.NAME).trim()));
        return getPage(request);
    }
}
