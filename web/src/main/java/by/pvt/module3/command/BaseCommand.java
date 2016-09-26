package by.pvt.module3.command;

import by.pvt.module3.entity.User;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.common.CommonService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by v on 08.09.2016.
 */
@Component
public abstract class BaseCommand<T> implements ActionCommand {

    protected static final DateFormat DF = new SimpleDateFormat("dd.MM.yyyy");
    public static final String ID = "id";
    protected static final String PAGE_NUM = "page_num";
    protected static final String CURRENT_PAGE = "current_page";
    private static final String PAGES = "numPages";
    private static final String COUNT_PAGES = "countPages";
    protected static final String ENTITY_EDIT = "entity";
    private static final String ENTITY_LIST = "entities";
    public static final String USER_ID = "user_id";
    private static final String INSERT_PAGE_NUM = "insertPageNum";

    protected Logger log = LogManager.getLogger(BaseCommand.class);

    @Autowired
    private CommonService<User> userService;

    @Autowired
    @Qualifier(value = "baseService")
    private CommonService<T> baseService;

    private String propPathEdit;
    private String propPathList;

    private Class persistentClass;

    public BaseCommand(Class persistentClass, String propPathEdit, String propPathList) {
        this.persistentClass = persistentClass;
        this.propPathEdit = propPathEdit;
        this.propPathList = propPathList;
    }

    private List<T> preparePagination(Integer pageNum, Model model) {
        // set pages
        List<Integer> pages;
        try {
            pages = getService().getPagesNums(persistentClass);
        } catch (Exception e) {
            handleException(e, model);
            pages = new ArrayList<Integer>();
        }
        model.addAttribute(PAGES, pages);
        model.addAttribute(COUNT_PAGES, pages.size());

        // set current page
        if (pages.size() > 0 && pageNum != null) {
            if (pageNum > pages.size())
                pageNum = pages.size();
        } else {
            pageNum = 1;
        }
        model.addAttribute(CURRENT_PAGE, pageNum);

        // set num page for new record
        try {
            model.addAttribute(INSERT_PAGE_NUM, getService().getInsertPageNum(persistentClass));
        } catch (Exception e) {
            handleException(e, model);
            model.addAttribute(INSERT_PAGE_NUM, 1);
        }

        try {
            return getService().getPage(persistentClass, pageNum);
        } catch (Exception e) {
            handleException(e, model);
        }
        return null;
    }

    public String getPage(Integer pageNum, Model model) {
        model.addAttribute(ENTITY_LIST, preparePagination(pageNum, model));
        return ConfigurationManager.getProperty(propPathList);
    }

    public CommonService<T> getService() {
        return baseService;
    }

    @Override
    public String execute(Map<String, String> paramMap, Model model, HttpSession httpSession) {
        return execute(paramMap, model);
    }


    public String execute(Map<String, String> paramMap, Model model) {
        String page;
        Integer num_page = 1;
        if (paramMap.get(PAGE_NUM) != null)
            num_page = Integer.parseInt(paramMap.get(PAGE_NUM).trim());
        if (paramMap.get(ID) != null) {
            T entity = null;
            try {
                entity = getSelectedEntity(Integer.parseInt(paramMap.get(ID).trim()), model);
            } catch (NumberFormatException e) {
                handleException(e, model);
            }
            try {
                initEditAttributes(entity, model);
            } catch (Exception e) {
                handleException(e, model);
            }
            if (entity != null)
                model.addAttribute(ENTITY_EDIT, entity);
            model.addAttribute(CURRENT_PAGE, num_page);
            page = ConfigurationManager.getProperty(propPathEdit);
        } else {
            page = getPage(num_page, model);
        }
        return page;
    }

    protected void initEditAttributes(T entity, Model model) {
    }

    protected String getPropPathEdit() {
        return propPathEdit;
    }

    protected T getSelectedEntity(Integer id, Model model) {
        T entity = null;
        if (id > 0) {
            entity = getService().getById(persistentClass, id);
        }
        return entity;
    }


    protected User getSessionUser(Model model) {
        Integer user_id = (Integer) model.asMap().get(USER_ID);
        try {
            return userService.getById(User.class, user_id);
        } catch (Exception e) {
            handleException(e, model);
        }
        return null;
    }

    protected String insert(T entity, Map<String, String> paramMap, Model model) {
        try {
            getService().add(entity);
        } catch (Exception e) {
            handleException(e, model);
        }
        return getPage(Integer.parseInt(paramMap.get(PAGE_NUM).trim()), model);
    }

    protected String update(T entity, Map<String, String> paramMap, Model model) {
        try {
            getService().update(entity);
        } catch (Exception e) {
            handleException(e, model);
        }
        return getPage(Integer.parseInt(paramMap.get(PAGE_NUM).trim()), model);
    }

    protected String delete(Map<String, String> paramMap, Model model) {
        try {
            getService().delete(persistentClass, Integer.parseInt(paramMap.get(ID).trim()));
        } catch (Exception e) {
            handleException(e, model);
        }
        return getPage(Integer.parseInt(paramMap.get(PAGE_NUM).trim()), model);
    }

    protected void handleException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("show_error", "true");
        String msg = e.getMessage() + "<br/><br/>";
        if (e.getCause() != null)
            msg += e.getCause().getMessage();
        model.addAttribute("text_error", msg);
    }
}
