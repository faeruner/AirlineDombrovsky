package by.pvt.module3.command.airline;

import by.pvt.module3.entity.Airline;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class UpdateAirlineCommand extends AirlineCommand {

    public String execute(Map<String, String> paramMap, Model model) {
        Airline airline = getService().getById(Airline.class, Integer.parseInt(paramMap.get(ID).trim()));
        updateEntity(airline, paramMap);
        return update(airline, paramMap, model);
    }
}
