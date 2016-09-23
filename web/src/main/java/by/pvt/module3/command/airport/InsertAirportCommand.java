package by.pvt.module3.command.airport;

import by.pvt.module3.entity.Airport;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class InsertAirportCommand extends AirportCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        return insert(new Airport(paramMap.get(Airport.NAME).trim()), paramMap, model);
    }
}
