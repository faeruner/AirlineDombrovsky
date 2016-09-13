package by.pvt.module3.command.airline;

import javax.servlet.http.HttpServletRequest;

public class DeleteAirlineCommand extends AirlineCommand {

    @Override
    public String execute(HttpServletRequest request) {
        getService().delete(Integer.parseInt(request.getParameter(ID).trim()));
        return getPage(request);
    }
}