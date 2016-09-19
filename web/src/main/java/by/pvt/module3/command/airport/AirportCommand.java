package by.pvt.module3.command.airport;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.service.AirportService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by v on 07.09.2016.
 */
public class AirportCommand extends BaseCommand<Airport> {

    public AirportCommand() {
        super(new AirportService(), "path.page.edit_airport", "path.page.airports");
    }

    protected void updateEntity(Airport airport, HttpServletRequest request) {
        airport.setName(request.getParameter(Airport.NAME).trim());
    }
}
