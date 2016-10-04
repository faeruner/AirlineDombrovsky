package by.pvt.module3.service.common;

import by.pvt.module3.dao.common.CommonDao;
import by.pvt.module3.entity.Fact;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public abstract class BaseService<T extends Fact> implements CommonService<T> {

    private Integer recordsPerPage = 3;

    private final CommonDao<T> dao;

    public BaseService(CommonDao<T> dao) {
        Assert.notNull(dao, "Dao must not be null!");
        this.dao = dao;
    }

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

    public List<Integer> getPagesNum() {
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
