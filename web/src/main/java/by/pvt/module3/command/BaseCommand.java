package by.pvt.module3.command;

import by.pvt.module3.entity.User;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.UserService;
import by.pvt.module3.service.common.CommonService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public class BaseCommand<T> implements ActionCommand {

    protected static final DateFormat DF = new SimpleDateFormat("dd.MM.yyyy");
    public static final String ID = "id";
    private static final String PAGE_NUM = "page_num";
    private static final String CURRENT_PAGE = "current_page";
    private static final String PAGES = "numPages";
    private static final String COUNT_PAGES = "countPages";
    protected static final String ENTITY_EDIT = "entity";
    private static final String ENTITY_LIST = "entities";
    private final static String USER_ID = "user_id";

    protected Logger log = LogManager.getRootLogger();

    private CommonService<T> service;
    private String propPathEdit;
    private String propPathList;

    public BaseCommand(CommonService<T> service, String propPathEdit, String propPathList){
        this.service = service;
        this.propPathEdit = propPathEdit;
        this.propPathList = propPathList;
    }

    private List<T> preparePagination(HttpServletRequest request) {
        Integer num_page = 1;
        if (request.getParameter(PAGE_NUM) != null)
            num_page = Integer.parseInt(request.getParameter(PAGE_NUM).trim());

        List<Integer> pages = service.getPagesNums();

        request.setAttribute(CURRENT_PAGE, num_page);
        request.setAttribute(PAGES, pages);
        request.setAttribute(COUNT_PAGES, pages.size());

        return service.getPage(num_page);
    }

    public String getPage(HttpServletRequest request) {
        request.setAttribute(ENTITY_LIST, preparePagination(request));
        return ConfigurationManager.getProperty(propPathList);
    }

    public CommonService<T> getService() {
        return service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        if (request.getParameter(ID) != null) {
            T entity = getSelectedEntity(Integer.parseInt(request.getParameter(ID).trim()), request);
            initEditAttributes(entity, request);
            if(entity != null)
                request.setAttribute(ENTITY_EDIT, entity);
            page = ConfigurationManager.getProperty(propPathEdit);
        } else {
            page = getPage(request);
        }
        return page;
    }

    protected void initEditAttributes(T entity, HttpServletRequest request) {
    }

    protected String getPropPathEdit() {
        return propPathEdit;
    }

    protected T getSelectedEntity(Integer id, HttpServletRequest request){
        T entity = null;
        if (id > 0) {
            entity = getService().getById(id);
        }
        return entity;
    }

    protected User getSessionUser(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        Integer user_id = (Integer) httpSession.getAttribute(USER_ID);
        CommonService<User> serviceUser = new UserService();
        return serviceUser.getById(user_id);
    }
}
