package by.pvt.module3.command.flight;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceFlight;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public abstract class FlightCommand extends BaseCommand<Flight> {

    public String getPage(HttpServletRequest request, CommonService service) {
        request.setAttribute("flight", preparePagination(request, service));
        return ConfigurationManager.getProperty("path.page.flights");
    }
}
