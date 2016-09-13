package by.pvt.module3.command.airport;

import by.pvt.module3.entity.Airport;

import javax.servlet.http.HttpServletRequest;

public class UpdateAirportCommand extends AirportCommand {

    public String execute(HttpServletRequest request) {
        getService().update(new Airport(Integer.parseInt(request.getParameter(ID).trim()),
                request.getParameter(Airport.NAME).trim()));
        return getPage(request);
    }
}
