package by.pvt.module3.command.airline;

import by.pvt.module3.entity.Airline;

import javax.servlet.http.HttpServletRequest;

public class InsertAirlineCommand extends AirlineCommand {

    public String execute(HttpServletRequest request) {
        getService().add(new Airline(request.getParameter(Airline.NAME).trim()));
        return getPage(request);
    }
}
