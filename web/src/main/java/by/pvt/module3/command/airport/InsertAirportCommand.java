package by.pvt.module3.command.airport;

import by.pvt.module3.entity.Airport;

import javax.servlet.http.HttpServletRequest;

public class InsertAirportCommand extends AirportCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return insert(new Airport(request.getParameter(Airport.NAME).trim()), request);
    }
}
