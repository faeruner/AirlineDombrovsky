package by.pvt.module3.command.flight;

import by.pvt.module3.entity.Flight;

import javax.servlet.http.HttpServletRequest;

public class UpdateFlightCommand extends FlightCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Flight flight = getService().getById(Integer.parseInt(request.getParameter(ID).trim()));
        updateEntity(flight, request);
        return update(flight, request);
    }
}
