package by.pvt.module3.command.flight;

import javax.servlet.http.HttpServletRequest;

public class DeleteFlightCommand extends FlightCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return delete(request);
    }
}