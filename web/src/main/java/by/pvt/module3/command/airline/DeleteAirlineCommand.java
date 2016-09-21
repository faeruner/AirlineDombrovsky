package by.pvt.module3.command.airline;

import javax.servlet.http.HttpServletRequest;

public class DeleteAirlineCommand extends AirlineCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return delete(request);
    }
}