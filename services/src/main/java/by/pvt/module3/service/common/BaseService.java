package by.pvt.module3.service.common;

import by.pvt.module3.dao.common.CommonDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 06.09.2016.
 */
public class BaseService<T> implements CommonService<T> {
    private static final Logger log = LogManager.getRootLogger();

    private Integer recordsPerPage = 3;

    protected CommonDAO<T> getDao() {
        return dao;
    }

    private CommonDAO<T> dao;

    public BaseService(CommonDAO<T> dao) {
        this.dao = dao;
    }

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }

    public T getById(Integer id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public Integer add(T entity) {
        return dao.add(entity);
    }

    public void update(T entity) {
        dao.update(entity);
    }

    public List<Integer> getPagesNums() {
        Long count = dao.getCount();
        List<Integer> listPages = new ArrayList<Integer>();
        for (int i = 1; count > 0; i++) {
            listPages.add(i);
            count -= getRecordsPerPage();
        }
        return listPages;
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public List<T> getPage(Integer numPage) {
        return dao.getPage(numPage, getRecordsPerPage());
    }

    public void setRecordsPerPage(Integer recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public Long getInsertPageNum() {
        Long count = dao.getCount();
        return count / getRecordsPerPage() + 1;
    }
}
