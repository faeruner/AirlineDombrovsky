package by.pvt.module3.command.flight;

import by.pvt.module3.entity.Flight;

import javax.servlet.http.HttpServletRequest;

public class InsertFlightCommand extends FlightCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Flight flight = new Flight();
        updateEntity(flight, request);
        getService().add(flight);
        return getPage(request);
    }
}
