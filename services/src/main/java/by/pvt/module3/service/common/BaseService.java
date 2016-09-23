package by.pvt.module3.service.common;

import by.pvt.module3.dao.common.BaseDAO;
import by.pvt.module3.dao.common.CommonDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 06.09.2016.
 */

@Service
@Transactional
public class BaseService<T> implements CommonService<T> {
    private static final Logger log = LogManager.getLogger(BaseService.class);

    private Integer recordsPerPage = 3;

    protected CommonDAO<T> getDao() {
        return dao;
    }

    @Autowired
    private BaseDAO<T> dao;

//    public BaseService(BaseDAO<T> dao) {
//        this.dao = dao;
//    }

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }

    public T getById(Class clazz, Integer id) {
        try {
            return getDao().getById(clazz, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Class clazz, Integer id) {
        getDao().delete(clazz, id);
    }

    public Integer add(T entity) {
        return getDao().add(entity);
    }

    public void update(T entity) {
        getDao().update(entity);
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
