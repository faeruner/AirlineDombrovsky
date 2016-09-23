package by.pvt.module3.command.crew;

import by.pvt.module3.entity.Crew;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class InsertCrewCommand extends CrewCommand {

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        Crew crew = new Crew();
        updateEntity(crew, paramMap, model);
        return insert(crew, paramMap, model);
    }
}
