package by.pvt.module3.command.airport;

import javax.servlet.http.HttpServletRequest;

public class DeleteAirportCommand extends AirportCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return delete(request);
    }
}
