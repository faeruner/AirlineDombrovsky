package by.pvt.module3.controller.common;

import by.pvt.module3.entity.Fact;
import by.pvt.module3.entity.User;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.common.CommonService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class ControllerUtils<T extends Fact> {
    protected Logger log = LogManager.getLogger(ControllerUtils.class);

    public static final DateFormat DF = new SimpleDateFormat("dd.MM.yyyy");

    private static final String COMMAND = "command";
    public static final String COMMAND_LIST = "list";
    private static final String COMMAND_EDIT = "edit";
    public static final String COMMAND_DEL = "del";
    public static final String COMMAND_ADD = "add";
    private static final String COMMAND_UPD = "upd";
    public static final String ID = "id";
    public static final String ENTITY = "entity";
    private static final String PAGE_NUM = "page_num";
    public static final String USER_ID = "user_id";
    private static final String PAGES = "numPages";
    private static final String COUNT_PAGES = "countPages";
    private static final String CURRENT_PAGE = "current_page";
    private static final String INSERT_PAGE_NUM = "insertPageNum";
    private static final String ENTITY_LIST = "entities";

    private String pathPageEdit;
    private String pathPageList;
    private Class persistentClass;

    private CommonService<T> commonService;

    @Autowired
    private CommonService<User> userService;

    public void init(String pathPageEdit, String pathPageList, Class persistentClass, CommonService<T> commonService) {
        this.pathPageEdit = pathPageEdit;
        this.pathPageList = pathPageList;
        this.persistentClass = persistentClass;
        this.commonService = commonService;
    }

    public User getSessionUser(HttpSession httpSession, Model model) {
        Integer user_id = (Integer) httpSession.getAttribute(USER_ID);
        if (user_id != null)
            try {
                return userService.getById(User.class, user_id);
            } catch (Exception e) {
                handleException(e, model);
            }
        return null;
    }

    public String getPage(Map<String, String> paramMap, Model model) {
        String command = paramMap.get(COMMAND);
        if (command != null)
            switch (command) {
                case COMMAND_ADD:
                    return insert(paramMap, model);
                case COMMAND_DEL:
                    return delete(paramMap, model);
                case COMMAND_EDIT:
                    return getEditPage(paramMap, model);
                case COMMAND_UPD:
                    return update(paramMap, model);
            }
        return fillModelPage(paramMap, model);
    }

    public T findById(Map<String, String> paramMap, Model model) {
        T entity = null;
        Integer id = getParamIntDef(paramMap, ID, -1);
        if (id > 0)
            try {
                entity = commonService.getById(persistentClass, id);
            } catch (Exception e) {
                handleException(e, model);
            }
        return entity;
    }

    public String insert(Map<String, String> paramMap, Model model) {
        T entity = (T) model.asMap().get(ENTITY);
        if (entity != null)
            try {
                commonService.add(entity);
            } catch (Exception e) {
                handleException(e, model);
            }
        return fillModelPage(paramMap, model);
    }

    public String delete(Map<String, String> paramMap, Model model) {
        try {
            commonService.delete(persistentClass, getParamIntDef(paramMap, ID, -1));
        } catch (Exception e) {
            handleException(e, model);
        }
        return fillModelPage(paramMap, model);
    }

    public T update(T entity, Model model) {
        if (entity != null)
            try {
                entity = commonService.update(persistentClass, entity);
            } catch (Exception e) {
                handleException(e, model);
            }
        return entity;
    }

    public String update(Map<String, String> paramMap, Model model) {
        update((T) model.asMap().get(ENTITY), model);
        return fillModelPage(paramMap, model);
    }

    public Integer getParamIntDef(Map<String, String> paramMap, String key, Integer def) {
        Integer id = def;
        if (paramMap.containsKey(key) && paramMap.get(key) != null)
            try {
                id = Integer.parseInt(paramMap.get(key).trim());
            } catch (NumberFormatException e) {
            }
        return id;
    }

    public String getEditPage(Map<String, String> paramMap, Model model) {
        model.addAttribute(CURRENT_PAGE, getParamIntDef(paramMap, PAGE_NUM, 1));
        return ConfigurationManager.getProperty(pathPageEdit);
    }

    public String fillModelPage(Map<String, String> paramMap, Model model) {
        model.addAttribute(ENTITY_LIST, preparePagination(getParamIntDef(paramMap, PAGE_NUM, 1), model));
        return ConfigurationManager.getProperty(pathPageList);
    }

    private List<T> preparePagination(Integer pageNum, Model model) {
        // set pages
        List<Integer> pages;
        try {
            pages = commonService.getPagesNums(persistentClass);
        } catch (Exception e) {
            handleException(e, model);
            pages = new ArrayList<>();
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
            model.addAttribute(INSERT_PAGE_NUM, commonService.getInsertPageNum(persistentClass));
        } catch (Exception e) {
            handleException(e, model);
            model.addAttribute(INSERT_PAGE_NUM, 1);
        }

        try {
            return commonService.getPage(persistentClass, pageNum);
        } catch (Exception e) {
            handleException(e, model);
        }
        return null;
    }

    private void handleException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("show_error", "true");
        String msg = e.getMessage() + "<br/><br/>";
        if (e.getCause() != null)
            msg += e.getCause().getMessage();
        model.addAttribute("text_error", msg);
    }
}
