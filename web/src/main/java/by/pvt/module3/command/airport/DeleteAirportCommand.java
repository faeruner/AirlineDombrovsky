package by.pvt.module3.command.airport;

import javax.servlet.http.HttpServletRequest;

public class DeleteAirportCommand extends AirportCommand {

    @Override
    public String execute(HttpServletRequest request) {
        getService().delete(Integer.parseInt(request.getParameter(ID).trim()));
        return getPage(request);
    }
}
