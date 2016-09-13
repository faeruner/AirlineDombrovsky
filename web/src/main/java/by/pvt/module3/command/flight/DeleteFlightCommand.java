package by.pvt.module3.command.flight;

import javax.servlet.http.HttpServletRequest;

public class DeleteFlightCommand extends FlightCommand {

    @Override
    public String execute(HttpServletRequest request) {
        getService().delete(Integer.parseInt(request.getParameter(ID).trim()));
        return getPage(request);
    }
}