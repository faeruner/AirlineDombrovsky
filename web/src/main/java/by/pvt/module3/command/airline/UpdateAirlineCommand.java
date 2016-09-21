package by.pvt.module3.command.airline;

import by.pvt.module3.entity.Airline;

import javax.servlet.http.HttpServletRequest;

public class UpdateAirlineCommand extends AirlineCommand {

    public String execute(HttpServletRequest request) {
        Airline airline = getService().getById(Integer.parseInt(request.getParameter(ID).trim()));
        updateEntity(airline, request);
        return update(airline, request);
    }
}
