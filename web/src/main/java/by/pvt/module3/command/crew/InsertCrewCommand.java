package by.pvt.module3.command.crew;

import by.pvt.module3.entity.Crew;

import javax.servlet.http.HttpServletRequest;

public class InsertCrewCommand extends CrewCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Crew crew = new Crew();
        updateEntity(crew, request);
        return insert(crew, request);
    }
}
