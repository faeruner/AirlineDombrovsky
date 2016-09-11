package by.pvt.module3.command.airline;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceAirline;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public abstract class AirlineCommand extends BaseCommand<Airline> {

    public String getPage(HttpServletRequest request, CommonService service) {
        request.setAttribute("airline", preparePagination(request, service));
        return ConfigurationManager.getProperty("path.page.airlines");
    }
}
