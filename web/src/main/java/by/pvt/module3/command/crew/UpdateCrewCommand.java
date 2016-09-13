package by.pvt.module3.command.crew;

import by.pvt.module3.entity.Crew;

import javax.servlet.http.HttpServletRequest;

public class UpdateCrewCommand extends CrewCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Crew crew = getService().getById(Integer.parseInt(request.getParameter(ID).trim()));
        updateEntity(crew, request);
        getService().update(crew);
        return getPage(request);
    }
}
