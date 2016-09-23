package by.pvt.module3.command.flight;

import by.pvt.module3.entity.Flight;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class UpdateFlightCommand extends FlightCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        Flight flight = getService().getById(Flight.class, Integer.parseInt(paramMap.get(ID).trim()));
        updateEntity(flight, paramMap, model);
        return update(flight, paramMap, model);
    }
}
