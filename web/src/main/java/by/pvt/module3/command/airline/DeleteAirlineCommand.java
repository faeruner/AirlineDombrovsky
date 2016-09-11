package by.pvt.module3.command.airline;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.entity.Airline;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceAirline;

public class DeleteAirlineCommand extends AirlineCommand {

    public String execute(HttpServletRequest request) {
        CommonService serviceAirline = new ServiceAirline();
        serviceAirline.delete(Integer.parseInt(request.getParameter(Airline.ID).trim()));

        return getPage(request, serviceAirline);
    }
}