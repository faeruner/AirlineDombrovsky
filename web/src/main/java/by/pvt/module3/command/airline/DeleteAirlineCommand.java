package by.pvt.module3.command.airline;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class DeleteAirlineCommand extends AirlineCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        return delete(paramMap, model);
    }
}