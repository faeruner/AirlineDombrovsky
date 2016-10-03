package by.pvt.module3.service.common;

import by.pvt.module3.dao.common.CommonDao;
import by.pvt.module3.entity.Fact;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 06.09.2016.
 */

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public abstract class BaseService<T extends Fact> implements CommonService<T> {
    private static final Logger log = LogManager.getLogger(BaseService.class);

    private Integer recordsPerPage = 3;

    @Autowired
    private CommonDao<T> dao;

    protected CommonDao<T> getDao() {
        return dao;
    }

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }

    public T getById(Integer id) {
        return getDao().getById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        getDao().delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer add(T entity) {
        return getDao().add(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public T update(T entity) {
        return getDao().update(entity);
    }

    public List<Integer> getPagesNums() {
        Long count = getDao().getCount();
        List<Integer> listPages = new ArrayList<Integer>();
        for (int i = 1; count > 0; i++) {
            listPages.add(i);
            count -= getRecordsPerPage();
        }
        return listPages;
    }

    public List<T> getAll() {
        return getDao().getAll();
    }

    public List<T> getPage(Integer numPage) {
        return getDao().getPage(numPage, getRecordsPerPage());
    }

    public void setRecordsPerPage(Integer recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public Long getInsertPageNum() {
        Long count = getDao().getCount();
        return count / getRecordsPerPage() + 1;
    }
}
