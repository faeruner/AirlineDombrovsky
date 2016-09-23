package by.pvt.module3.command.airport;

import by.pvt.module3.entity.Airport;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class UpdateAirportCommand extends AirportCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        Airport airport = getService().getById(Airport.class, Integer.parseInt(paramMap.get(ID).trim()));
        updateEntity(airport, paramMap);
        return update(airport, paramMap, model);
    }
}
