package by.pvt.module3.command.crew;

import javax.servlet.http.HttpServletRequest;

public class DeleteCrewCommand extends CrewCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return delete(request);
    }
}
