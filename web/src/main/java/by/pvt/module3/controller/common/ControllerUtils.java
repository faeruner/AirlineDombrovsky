package by.pvt.module3.controller.common;

import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.common.BaseService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ControllerUtils<T> {
    protected Logger log = LogManager.getLogger(ControllerUtils.class);

    public static final String ID = "id";
    public static final String ENTITY = "entity";
    public static final String PAGE_NUM = "page_num";
    public static final String USER_ID = "user_id";
    private static final String PAGES = "numPages";
    private static final String COUNT_PAGES = "countPages";
    private static final String CURRENT_PAGE = "current_page";
    private static final String INSERT_PAGE_NUM = "insertPageNum";
    private static final String ENTITY_LIST = "entities";

    private String pathPageEdit;
    private String pathPageList;
    private Class persistentClass;
    private BaseService<T> baseService;

    public void init(String pathPageEdit, String pathPageList, Class persistentClass, BaseService<T> baseService) {
        this.pathPageEdit = pathPageEdit;
        this.pathPageList = pathPageList;
        this.persistentClass = persistentClass;
        this.baseService = baseService;
    }

    public T findById(Map<String, String> paramMap, Model model) {
        T entity = null;
        try {
            entity = baseService.getById(persistentClass, getParamIntDef(paramMap, ControllerUtils.ID, -1));
        } catch (Exception e) {
            handleException(e, model);
        }
        return entity;
    }

    public String insert(T entity, Map<String, String> paramMap, Model model) {
        try {
            baseService.add(entity);
        } catch (Exception e) {
            handleException(e, model);
        }
        return fillModelPage(paramMap, model);
    }

    public String delete(Map<String, String> paramMap, Model model) {
        try {
            baseService.delete(persistentClass, getParamIntDef(paramMap, ControllerUtils.ID, -1));
        } catch (Exception e) {
            handleException(e, model);
        }
        return fillModelPage(paramMap, model);
    }

    public String update(T entity, Map<String, String> paramMap, Model model) {
        try {
            baseService.update(entity);
        } catch (Exception e) {
            handleException(e, model);
        }
        return fillModelPage(paramMap, model);
    }

    private Integer getParamIntDef(Map<String, String> paramMap, String key, Integer def) {
        Integer id = def;
        if (paramMap.containsKey(key) && paramMap.get(key) != null)
            try {
                id = Integer.parseInt(paramMap.get(key).trim());
            } catch (NumberFormatException e) {
            }
        return id;
    }

    public String fillModelEntity(Map<String, String> paramMap, Model model) {
        Integer id = getParamIntDef(paramMap, ControllerUtils.ID, -1);
        if (id > 0)
            try {
                T entity = baseService.getById(persistentClass, id);
                if (entity != null)
                    model.addAttribute(ENTITY, entity);
            } catch (NumberFormatException e) {
                handleException(e, model);
            } catch (Exception e) {
                handleException(e, model);
            }
        return ConfigurationManager.getProperty(pathPageEdit);
    }

    public String fillModelPage(Map<String, String> paramMap, Model model) {
        model.addAttribute(ENTITY_LIST, preparePagination(getParamIntDef(paramMap, ControllerUtils.PAGE_NUM, 1), model));
        return ConfigurationManager.getProperty(pathPageList);
    }

    private List<T> preparePagination(Integer pageNum, Model model) {
        // set pages
        List<Integer> pages;
        try {
            pages = baseService.getPagesNums(persistentClass);
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
            model.addAttribute(INSERT_PAGE_NUM, baseService.getInsertPageNum(persistentClass));
        } catch (Exception e) {
            handleException(e, model);
            model.addAttribute(INSERT_PAGE_NUM, 1);
        }

        try {
            return baseService.getPage(persistentClass, pageNum);
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
