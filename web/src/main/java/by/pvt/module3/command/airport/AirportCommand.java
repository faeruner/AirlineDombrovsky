package by.pvt.module3.command.airport;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Airport;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by v on 07.09.2016.
 */
@Component
public class AirportCommand extends BaseCommand<Airport> {

    public AirportCommand() {
        super(Airport.class, "path.page.edit_airport", "path.page.airports");
    }

    protected void updateEntity(Airport airport, Map<String, String> paramMap) {
        airport.setName(paramMap.get(Airport.NAME).trim());
    }
}
