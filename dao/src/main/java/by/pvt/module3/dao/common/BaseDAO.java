package by.pvt.module3.dao.common;

import by.pvt.module3.entity.Fact;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BaseDAO<T extends Fact> implements CommonDAO<T> {
    protected final static Logger log = LogManager.getRootLogger();
    private final static String ID = "id";

    public Integer add(T entity) {
        Session session = SessionUtil.getSesson();
        Integer id = (Integer) session.save(entity);
        session.flush();
        return id;
    }

    protected void prepareDelete(T t) {

    }

    public void delete(Class clazz, Integer id) {
        Session session = SessionUtil.getSesson();
        T t = (T) session.get(clazz, id);
        prepareDelete(t);
        session.delete(t);
        session.flush();
    }

    public T update(Class clazz, T entity) {
        Session session = SessionUtil.getSesson();
        session.update(entity);
        session.flush();
        entity = (T) session.get(clazz, entity.getId());
        return entity;
    }

    public T getById(Class clazz, Integer id) {
        Session session = SessionUtil.getSesson();
        T t = (T) session.load(clazz, id);
        return t;
    }

    public ArrayList<T> getAll(Class clazz) {
        Session session = SessionUtil.getSesson();
        ArrayList<T> listT = (ArrayList<T>) session.createCriteria(clazz).list();
        return listT;
    }

    public Long getCount(Class clazz) {
        Session session = SessionUtil.getSesson();
        String hql = "select count(*) from " + SessionUtil.getEntityByClass(clazz);
        Query queryCount = session.createQuery(hql);
        Long count = (Long) queryCount.uniqueResult();
        return count;
    }

    public List<T> getPage(Class clazz, Integer pageNum, Integer recordsPerPage) {
        return getPage(clazz, pageNum, recordsPerPage, ID);
    }

    public List<T> getPage(Class clazz, Integer pageNum, Integer recordsPerPage, String orderBy) {
        Session session = SessionUtil.getSesson();
        String hql = new StringBuilder().append("from ").append(SessionUtil.getEntityByClass(clazz)).append(" order by ").append(orderBy).toString();
        Query query = session.createQuery(hql);
        if (pageNum == null)
            pageNum = 1;
        if (!SessionUtil.driverUsed(SessionUtil.SQLITE_JDBC) || pageNum == 1) {
            query.setFirstResult((pageNum - 1) * recordsPerPage);
            query.setMaxResults(recordsPerPage);
        } else {
            query.setMaxResults((pageNum - 1) * recordsPerPage);
            query.setFirstResult(recordsPerPage);
        }
        List<T> listT = (List<T>) query.list();
        return listT;
    }
}
