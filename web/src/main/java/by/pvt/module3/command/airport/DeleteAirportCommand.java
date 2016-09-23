package by.pvt.module3.command.airport;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class DeleteAirportCommand extends AirportCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        return delete(paramMap, model);
    }
}
