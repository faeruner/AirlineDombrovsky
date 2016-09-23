package by.pvt.module3.command.flight;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class DeleteFlightCommand extends FlightCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        return delete(paramMap, model);
    }
}