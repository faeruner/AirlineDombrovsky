package by.pvt.module3.command.crew;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceCrew;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public abstract class CrewCommand extends BaseCommand<Crew> {

    public String getPage(HttpServletRequest request, CommonService service) {
        request.setAttribute("crew", preparePagination(request, service));
        return ConfigurationManager.getProperty("path.page.crews");
    }
}
