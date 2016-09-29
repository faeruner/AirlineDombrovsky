package by.pvt.module3.service.common;

import by.pvt.module3.dao.common.CommonDAO;
import by.pvt.module3.entity.Fact;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 06.09.2016.
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BaseService<T extends Fact> implements CommonService<T> {
    private static final Logger log = LogManager.getLogger(BaseService.class);

    private Integer recordsPerPage = 3;

    protected CommonDAO<T> getDao() {
        return dao;
    }

    @Autowired
    @Qualifier(value = "baseDAO")
    private CommonDAO<T> dao;

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }

    public T getById(Class clazz, Integer id) {
        return getDao().getById(clazz, id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Class clazz, Integer id) {
        getDao().delete(clazz, id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer add(T entity) {
        return getDao().add(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public T update(Class clazz, T entity) {
        return getDao().update(clazz, entity);
    }

    public List<Integer> getPagesNums(Class clazz) {
        Long count = getDao().getCount(clazz);
        List<Integer> listPages = new ArrayList<Integer>();
        for (int i = 1; count > 0; i++) {
            listPages.add(i);
            count -= getRecordsPerPage();
        }
        return listPages;
    }

    public List<T> getAll(Class clazz) {
        return getDao().getAll(clazz);
    }

    public List<T> getPage(Class clazz, Integer numPage) {
        return getDao().getPage(clazz, numPage, getRecordsPerPage());
    }

    public void setRecordsPerPage(Integer recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public Long getInsertPageNum(Class clazz) {
        Long count = getDao().getCount(clazz);
        return count / getRecordsPerPage() + 1;
    }
}
