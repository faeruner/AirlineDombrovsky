package by.pvt.module3.command.staff;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceStaff;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public abstract class StaffCommand extends BaseCommand<Staff> {

    public String getPage(HttpServletRequest request, CommonService service) {
        request.setAttribute("staff", preparePagination(request, service));
        return ConfigurationManager.getProperty("path.page.staff");
    }
}
