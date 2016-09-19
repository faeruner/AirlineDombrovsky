package by.pvt.module3.command.airport;

import by.pvt.module3.entity.Airport;

import javax.servlet.http.HttpServletRequest;

public class UpdateAirportCommand extends AirportCommand {

    public String execute(HttpServletRequest request) {
        Airport airport = getService().getById(Integer.parseInt(request.getParameter(ID).trim()));
        updateEntity(airport, request);
        getService().update(airport);
        return getPage(request);
    }
}
