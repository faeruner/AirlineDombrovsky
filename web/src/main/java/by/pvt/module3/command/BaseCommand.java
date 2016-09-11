package by.pvt.module3.command;

import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public abstract class BaseCommand<T> implements ActionCommand {

    public List<T> preparePagination(HttpServletRequest request, CommonService service) {
        Integer num_page = 1;
        if (request.getParameter("page_num") != null)
            num_page = Integer.parseInt(request.getParameter("page_num").trim());

        List<Integer> pages = service.getPagesNums();

        request.setAttribute("current_page", num_page);
        request.setAttribute("numPages", pages);
        request.setAttribute("countPages", pages.size());
        return service.getPage(num_page);
    }

}
