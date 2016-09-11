package by.pvt.module3.command.user;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.User;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public abstract class UserCommand extends BaseCommand<User> {

    public String getPage(HttpServletRequest request, CommonService service) {
        request.setAttribute("user", preparePagination(request, service));
        return ConfigurationManager.getProperty("path.page.users");
    }
}
