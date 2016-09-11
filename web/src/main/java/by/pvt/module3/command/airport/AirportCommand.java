package by.pvt.module3.command.airport;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceAirport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by v on 07.09.2016.
 */
public abstract class AirportCommand extends BaseCommand<Airport> {

    public String getPage(HttpServletRequest request, CommonService service) {
        request.setAttribute("airport", preparePagination(request, service));
        return ConfigurationManager.getProperty("path.page.airports");
    }
}
