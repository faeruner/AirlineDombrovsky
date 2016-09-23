package by.pvt.module3.command.airline;

import by.pvt.module3.entity.Airline;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class InsertAirlineCommand extends AirlineCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        return insert(new Airline(paramMap.get(Airline.NAME).trim()), paramMap, model);
    }
}
