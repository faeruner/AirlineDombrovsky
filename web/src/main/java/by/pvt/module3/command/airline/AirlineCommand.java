package by.pvt.module3.command.airline;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Airline;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by v on 08.09.2016.
 */
@Component
public class AirlineCommand extends BaseCommand<Airline> {

    public AirlineCommand() {
        super(Airline.class, "path.page.edit_airline", "path.page.airlines");
    }

    protected void updateEntity(Airline airline, Map<String, String> paramMap) {
        airline.setName(paramMap.get(Airline.NAME).trim());
    }
}
