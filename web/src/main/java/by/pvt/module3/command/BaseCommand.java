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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public class BaseCommand<T> implements ActionCommand {

    protected final static DateFormat DF = new SimpleDateFormat("dd.MM.yyyy");
    public final static String ID = "id";
    private final static String PAGE_NUM = "page_num";
    private final static String CURRENT_PAGE = "current_page";
    private final static String PAGES = "numPages";
    private final static String COUNT_PAGES = "countPages";
    protected final static String ENTITY_EDIT = "entity";
    private final static String ENTITY_LIST = "entities";
    private final static String USER_ID = "user_id";
    private final static String INSERT_PAGE_NUM = "insertPageNum";

    protected Logger log = LogManager.getRootLogger();

    private CommonService<T> service;
    private String propPathEdit;
    private String propPathList;

    public BaseCommand(CommonService<T> service, String propPathEdit, String propPathList) {
        this.service = service;
        this.propPathEdit = propPathEdit;
        this.propPathList = propPathList;
    }

    private List<T> preparePagination(HttpServletRequest request) {
        // set pages
        List<Integer> pages;
        try {
            pages = service.getPagesNums();
        } catch (Exception e) {
            handleException(e, request);
            pages = new ArrayList<Integer>();
        }
        request.setAttribute(PAGES, pages);
        request.setAttribute(COUNT_PAGES, pages.size());

        // set current page
        Integer num_page = 1;
        if (pages.size() > 0 && request.getParameter(PAGE_NUM) != null) {
            num_page = Integer.parseInt(request.getParameter(PAGE_NUM).trim());
            if (num_page > pages.size())
                num_page = pages.size();
        }
        request.setAttribute(CURRENT_PAGE, num_page);

        // set num page for new record
        try {
            request.setAttribute(INSERT_PAGE_NUM, service.getInsertPageNum());
        } catch (Exception e) {
            handleException(e, request);
            request.setAttribute(INSERT_PAGE_NUM, 1);
        }

        try {
            return service.getPage(num_page);
        } catch (Exception e) {
            handleException(e, request);
        }
        return null;
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
            T entity = null;
            try {
                entity = getSelectedEntity(Integer.parseInt(request.getParameter(ID).trim()), request);
            } catch (NumberFormatException e) {
                handleException(e, request);
            }
            try {
                initEditAttributes(entity, request);
            } catch (Exception e) {
                handleException(e, request);
            }
            if (entity != null)
                request.setAttribute(ENTITY_EDIT, entity);
            Integer num_page = 1;
            if (request.getParameter(PAGE_NUM) != null)
                num_page = Integer.parseInt(request.getParameter(PAGE_NUM).trim());
            request.setAttribute(CURRENT_PAGE, num_page);
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

    protected T getSelectedEntity(Integer id, HttpServletRequest request) {
        T entity = null;
        if (id > 0) {
            entity = getService().getById(id);
        }
        return entity;
    }

    protected User getSessionUser(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Integer user_id = (Integer) httpSession.getAttribute(USER_ID);
        CommonService<User> serviceUser = new UserService();
        try {
            return serviceUser.getById(user_id);
        } catch (Exception e) {
            handleException(e, request);
        }
        return null;
    }

    protected String insert(T entity, HttpServletRequest request) {
        try {
            getService().add(entity);
        } catch (Exception e) {
            handleException(e, request);
        }
        return getPage(request);
    }

    protected String update(T entity, HttpServletRequest request) {
        try {
            getService().update(entity);
        } catch (Exception e) {
            handleException(e, request);
        }
        return getPage(request);
    }

    protected String delete(HttpServletRequest request) {
        try {
            getService().delete(Integer.parseInt(request.getParameter(ID).trim()));
        } catch (Exception e) {
            handleException(e, request);
        }
        return getPage(request);
    }

    protected void handleException(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        request.setAttribute("show_error", "true");
        String msg = e.getMessage() + "<br/><br/>";
        if (e.getCause() != null)
            msg += e.getCause().getMessage();
        request.setAttribute("text_error", msg);
    }
}
