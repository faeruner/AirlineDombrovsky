package by.pvt.module3.dao.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BaseDAO<T> implements CommonDAO<T> {
    protected static final Logger log = LogManager.getRootLogger();
    private Class persistentClass;

    public BaseDAO(Class persistentClass) {
        this.persistentClass = persistentClass;
    }

    private Class getPersistentClass() {
        return persistentClass;
    }

    public Integer add(T entity) {
        Session session = SessionUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            Integer id = (Integer) session.save(entity);
            transaction.commit();
            return id;
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
        return null;
    }

    public void delete(Integer id) {
        Session session = SessionUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            T t = (T) session.get(getPersistentClass(), id);
            session.delete(t);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void update(T entity) {
        Session session = SessionUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public T getById(Integer id) {
        Session session = SessionUtil.getSesson();
        return (T) session.get(getPersistentClass(), id);
    }

    public ArrayList<T> getAll() {
        Session session = SessionUtil.getSesson();
        return (ArrayList<T>) session.createCriteria(getPersistentClass()).list();
    }

    public Long getCount() {
        String hql = "select count(*) from " + SessionUtil.getEntityByClass(getPersistentClass());
        Query queryCount = SessionUtil.getSesson().createQuery(hql);
        return (Long) queryCount.uniqueResult();
    }

    public List<T> getPage(Integer pageNum, Integer recordsPerPage) {
        return getPage(pageNum, recordsPerPage, "id");
    }

    public List<T> getPage(Integer pageNum, Integer recordsPerPage, String orderBy) {
        String hql = new StringBuilder().append("from ").append(SessionUtil.getEntityByClass(getPersistentClass())).append(" order by ").append(orderBy).toString();
        Session session = SessionUtil.getSesson();
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
        return (List<T>) query.list();
    }
}
